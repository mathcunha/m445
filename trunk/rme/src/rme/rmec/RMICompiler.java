// NinjaRMI, by Matt Welsh (mdw@cs.berkeley.edu)
// See http://www.cs.berkeley.edu/~mdw/proj/ninja for details

/*
 * "Copyright (c) 1998 by The Regents of the University of California
 *  All rights reserved."
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written agreement is
 * hereby granted, provided that the above copyright notice and the following
 * two paragraphs appear in all copies of this software.
 *
 * IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
 * OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
 * CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
 * ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
 * PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 */

package rme.rmec;

import java.lang.reflect.*;
import java.io.*;
import java.util.Vector;
import java.util.StringTokenizer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * RMICompiler is a class which generates Java sourcecode implementing RMI stubs
 * and skeletons, given a class which implements interfaces extending
 * <tt>java.rmi.Remote</tt>. I've left the methods public here in case you
 * come up with a cunning way to use RMICompiler within another program. The
 * usual way to use this class is to invoke
 * <tt>ninja.rmi.compiler.NinjaRMIC</tt> which is a <tt>main</tt> front-end
 * to it.
 */
public class RMICompiler {
	Class cl;

	ClassDecomposer cldc;

	int mods;

	String stubname, skelname;

	Vector allmethods, allintfs;

	String class_shortname = null, packagename = null;

	private long hashval = 0;

	/**
	 * Create an RMICompiler for the given class.
	 */
	RMICompiler(String classname) throws IllegalArgumentException,
			ClassNotFoundException {
		StringTokenizer st = new StringTokenizer(classname, ".");
		boolean first = true;
		while (st.hasMoreTokens()) {
			String t = st.nextToken();
			if (st.hasMoreTokens()) {
				if (!first) {
					packagename = packagename + "." + t;
				} else {
					packagename = t;
					first = false;
				}
			} else {
				class_shortname = t;
			}
		}

		// XXX mdw: This causes the static initializers for the class to be
		// executed; would like to load the classfile by hand and get its list
		// of interfaces.
		cl = Class.forName(classname);
		allmethods = new Vector();
		allintfs = new Vector();

		Class intfs[] = cl.getInterfaces();
		int i;
		boolean somefound = false;
		for (i = 0; i < intfs.length; i++) {
			Class theints[] = intfs[i].getInterfaces();
			boolean found = false;
			if (theints != null) {
				int j;
				for (j = 0; j < theints.length; j++) {
					if (theints[j].getName().equals("arcademis.Remote")) {
						found = true;
						break;
					}
				}
			}
			if (found) {
				// This interface extends Remote
				allintfs.addElement(intfs[i]);
				somefound = true;
				ClassDecomposer decomp = new ClassDecomposer(intfs[i]);
				MethodDecomposer md;
				while ((md = decomp.getNextMethod()) != null) {
					int methmods = md.getMethod().getModifiers();
					if (!Modifier.isPublic(methmods))
						continue;

					// OK, do we already have a matching method identifier?
					int k;
					boolean methodfound = false;
					for (k = 0; k < allmethods.size(); k++) {
						if (methodsEqual(((MethodDecomposer) allmethods
								.elementAt(k)), md))
							methodfound = true;
					}
					if (!methodfound)
						allmethods.addElement(md);
				}
			}
		}
		if (!somefound)
			throw new IllegalArgumentException(
					"Class "
							+ classname
							+ " does not implement any interfaces which extend arcademis.Remote.");

		this.stubname = class_shortname + "_Stub";
		this.skelname = class_shortname + "_Skeleton";
	}

	/**
	 * Returns the name of the generated stub class.
	 */
	public String getStubname() {
		return stubname;
	}

	/**
	 * Returns the name of the generated skeleton class.
	 */
	public String getSkelname() {
		return skelname;
	}

	/**
	 * Returns the name of the package which the class used by RMICompiler is
	 * in.
	 */
	public String getPackagename() {
		return packagename;
	}

	/**
	 * Returns the name of the class, without the package prefix, used by the
	 * RMICompiler.
	 */
	public String getClassShortname() {
		return class_shortname;
	}

	// Stub methods ************************************************************

	/**
	 * Writes the Java sourcecode for the generated stub to the given
	 * PrintWriter.
	 */
	public void writeStubOutput(PrintWriter os) throws IllegalArgumentException {
		writeStubHeader(os);
		writeStubClassDesc(os);
		writeStubMethods(os);
		writeStubClosing(os);
	}

	private void writeStubHeader(PrintWriter os) {
		os.println("/* Generated by RMICompiler.java -- do not edit */");
		if (packagename != null) {
			os.println("\npackage " + packagename + ";");
		}
		os.println("\nimport rme.*;");
		os.println("import arcademis.*;\n");
	}

	private void writeStubClassDesc(PrintWriter os) {
		os.print("public class " + stubname
				+ " extends MultiServerStub implements ");
		int i;
		for (i = 0; i < allintfs.size(); i++) {
			os.print(((Class) allintfs.elementAt(i)).getName());
			if (i != allintfs.size() - 1) {
				os.print(", ");
			}
		}
		os.println(" {");
	}

	private void writeStubMethods(PrintWriter os)
			throws IllegalArgumentException {

		MethodDecomposer md;

		int methodnum;
		int i;

		for (methodnum = 0; methodnum < allmethods.size(); methodnum++) {
			md = (MethodDecomposer) allmethods.elementAt(methodnum);

			Method m = md.getMethod();
			int methmods = m.getModifiers();
			if (!Modifier.isPublic(methmods))
				continue;

			// Check that method throws arcademis.ArcademisException
			if (!throwsRemoteException(m, "arcademis.ArcademisException"))
				throw new IllegalArgumentException("Method " + m.getName()
						+ " must throw arcademis.ArcademisException.");

			TypeDecomposer td = new TypeDecomposer(m.getReturnType());
			os.print("  public " + td.toString() + " " + m.getName());
			os.println(md.getParamsDesc() + md.getThrowsDesc() + " {");

			ParameterDecomposer params[] = md.getParams();

			// Method Body
			if (!m.getReturnType().getName().equals("void"))
				os.println("    " + td.toString() + " resp = "
						+ initializer(m.getReturnType()) + ";");
			else
				os.println("    Object resp = "
						+ initializer(m.getReturnType()) + ";");
			os.println("    try {");
			os.println("      Stream args = OrbAccessor.getStream();");

			if (params != null)
				if (params.length > 0)
					// Method has arguments
					for (i = 0; i < params.length; i++)
						os.println("      args.write(" + params[i].getName()
								+ ");");

			os.println("      int op = " + methodnum + ";");
			os.println("      Stream future = invoke(args, op, '?', 0);");
			os.println("      if(future.isException()) {");
			os.println("        Exception e = (Exception)future.readObject();");
			os.println("        throw e;");
			os.println("      }");

			if (!m.getReturnType().getName().equals("void")) {
				// Method has return
				td = new TypeDecomposer(m.getReturnType());
				os.println("      resp = (" + td.toString() + ")future."
						+ objinread(m.getReturnType()) + "();");
			} else {
				// No return value
				os.println("      resp = future.readObject();");
			}

			// write routines for treating the exceptions that can be thrown by
			// this method
			String ex[] = getExceptionNames(m);
			for (int x = 0; x < ex.length; x++) {
				os.println("    } catch (" + ex[x] + " e) {");
				os.println("      throw e;");
			}

			if (!throwsRemoteException(m, "java.lang.Exception")) {
				os.println("    } catch (Exception e) {");
				os
						.println("      throw new arcademis.UnspecifiedException(e.toString());");
			}
			os.println("    }");

			if (!m.getReturnType().getName().equals("void"))
				os.println("    return resp;");

			os.println("  }\n");
		}
	}

	private void writeStubClosing(PrintWriter os) {
		os.println("  // End of generated code");
		os.print("}");
	}

	// Skeleton methods *******************************************************

	/**
	 * Writes the Java sourcecode for the generated skeleton to the given
	 * PrintWriter.
	 */
	public void writeSkelOutput(PrintWriter os) throws IllegalArgumentException {
		writeSkelHeader(os);
		writeSkelClassDesc(os);
		writeSkelMethods(os);
		writeSkelClosing(os);
	}

	private void writeSkelHeader(PrintWriter os) {
		os.println("/* Generated by RMICompiler.java -- do not edit */");
		if (packagename != null) {
			os.println("\npackage " + packagename + ";");
		}
		os.println("\nimport rme.*;");
		os.println("import rme.server.*;\n");
		os.println("import arcademis.*;\n");
	}

	private void writeSkelClassDesc(PrintWriter os) {
		os.println("public class " + skelname
				+ " extends arcademis.server.Skeleton {");
	}

	private void writeSkelMethods(PrintWriter os)
			throws IllegalArgumentException {
		os
				.println("\n  public Stream dispatch(RemoteCall r) throws Exception {");
		os.println("\n    RmeRemoteCall remoteCall = (RmeRemoteCall)r;");
		os.println("    Stream returnStr = OrbAccessor.getStream();");
		os.println("    Stream args = remoteCall.getArguments();\n");
		os.println("    switch (remoteCall.getOperationCode()) {");

		int methodnum;
		MethodDecomposer md;

		for (methodnum = 0; methodnum < allmethods.size(); methodnum++) {
			md = (MethodDecomposer) allmethods.elementAt(methodnum);

			Method m = md.getMethod();
			int methmods = m.getModifiers();
			if (!Modifier.isPublic(methmods))
				continue;

			if (!throwsRemoteException(m, "arcademis.ArcademisException"))
				throw new IllegalArgumentException("Method " + m.getName()
						+ " throw java.lang.Exception.");

			os.println("      case " + methodnum + ": {");

			ParameterDecomposer params[] = md.getParams();
			if (params != null) {
				// Method has arguments
				for (int i = 0; i < params.length; i++) {
					os.println("        " + params[i].getTypeName() + " "
							+ params[i].getName() + " = ("
							+ params[i].getTypeName() + ")args."
							+ objinread(params[i].getType()) + "();");
				}
			}

			TypeDecomposer td = new TypeDecomposer(m.getReturnType());

			if (!m.getReturnType().getName().equals("void")) {
				// Method has return value
				os.print("        " + td.toString() + " retValue = ");
			} else {
				os.println("        Marshalable retValue = null;");
				os.print("        ");
			}

			os.print("((" + cl.getName() + ")super.remoteObject)."
					+ m.getName() + "(");
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					os.print(params[i].getName());
					if (i != params.length - 1)
						os.print(", ");
				}
			}
			os.println(");");
			os.println("        returnStr.write(retValue);");

			os.println("      }");
			os.println("      break;");
		}

		os.println("      default: {");
		os
				.println("        throw new ArcademisException(\"Invalid operation in remote method request\");");
		os.println("      }");
		os.println("    }    // end switch");
		os.println("\n    return returnStr;");
		os.println("  }    // end dispatch\n");
	}

	private void writeSkelClosing(PrintWriter os) {
		os.println("}");
	}

	// Check that method throws java.rmi.RemoteException
	private boolean throwsRemoteException(Method m, String e) {
		Class ex[] = m.getExceptionTypes();
		int i;
		boolean found = false;
		for (i = 0; i < ex.length; i++) {
			if (ex[i].getName().equals(e)) {
				found = true;
				break;
			}
		}
		return found;
	}

	// return an array with all the exceptions that can be rised by the method
	private String[] getExceptionNames(Method m) {
		Class ex[] = m.getExceptionTypes();
		sort(ex);
		String list[] = new String[ex.length];

		for (int i = 0, j = 0; i < ex.length; i++) {
			list[j++] = ex[i].getName();
		}
		return list;
	}

	// order a vector of classes so that the most general classes stay in higher
	// positions in the vector
	private void sort(Class v[]) {
		for (int i = 0; i < v.length - 1; i++) {
			int mark = i;
			for (int j = i + 1; j < v.length; j++) {
				if (v[mark].isAssignableFrom(v[j])) {
					mark = j;
				}
			}
			Class aux = v[mark];
			v[mark] = v[i];
			v[i] = aux;
		}
	}

	// Return true if methods have same name and signature
	private boolean methodsEqual(MethodDecomposer m1, MethodDecomposer m2) {
		return m1.equals(m2);
	}

	// Given a class, determine the proper value to initialize one of its
	// instance
	private String initializer(Class type) {
		if (type.isAssignableFrom(Boolean.TYPE))
			return "false";
		if (type.isAssignableFrom(Byte.TYPE))
			return "0";
		if (type.isAssignableFrom(Character.TYPE))
			return "0";
		if (type.isAssignableFrom(Double.TYPE))
			return "0.0";
		if (type.isAssignableFrom(Float.TYPE))
			return "0.0";
		if (type.isAssignableFrom(Integer.TYPE))
			return "0";
		if (type.isAssignableFrom(Long.TYPE))
			return "0L";
		if (type.isAssignableFrom(Short.TYPE))
			return "0";
		return "null";
	}

	// Given a class, determine the appropriate method of ObjectInput to
	// call to read it
	private String objinread(Class type) {
		if (type.isAssignableFrom(Boolean.TYPE))
			return "readBoolean";
		if (type.isAssignableFrom(Byte.TYPE))
			return "readByte";
		if (type.isAssignableFrom(Character.TYPE))
			return "readChar";
		if (type.isAssignableFrom(Double.TYPE))
			return "readDouble";
		if (type.isAssignableFrom(Float.TYPE))
			return "readFloat";
		if (type.isAssignableFrom(Integer.TYPE))
			return "readInt";
		if (type.isAssignableFrom(Long.TYPE))
			return "readLong";
		if (type.isAssignableFrom(Short.TYPE))
			return "readShort";
		return "readObject";
	}

	// Given a class, determine the appropriate method of ObjectOutput to
	// call to write it
	private String objoutwrite(Class type) {
		if (!type.isPrimitive())
			return "writeObject";

		if (type.isAssignableFrom(Boolean.TYPE))
			return "writeBoolean";
		if (type.isAssignableFrom(Byte.TYPE))
			return "writeByte";
		if (type.isAssignableFrom(Character.TYPE))
			return "writeChar";
		if (type.isAssignableFrom(Double.TYPE))
			return "writeDouble";
		if (type.isAssignableFrom(Float.TYPE))
			return "writeFloat";
		if (type.isAssignableFrom(Integer.TYPE))
			return "writeInt";
		if (type.isAssignableFrom(Long.TYPE))
			return "writeLong";
		if (type.isAssignableFrom(Short.TYPE))
			return "writeShort";

		else
			return "writeObject()"; // XXX mdw probably not the right thing
	}

	private long genHashval() {
		if (hashval != 0)
			return hashval;
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			System.out
					.println("RMICompiler: Warning: No SHA MessageDigest algorithm found (using bogus hashvalue).");
			return 0x42;
		}
		if (packagename != null)
			sha.update(packagename.getBytes());
		sha.update(class_shortname.getBytes());

		int methodnum;
		MethodDecomposer md;

		for (methodnum = 0; methodnum < allmethods.size(); methodnum++) {
			md = (MethodDecomposer) allmethods.elementAt(methodnum);
			Method m = md.getMethod();
			sha.update(m.getName().getBytes());
			TypeDecomposer td = new TypeDecomposer(m.getReturnType());
			sha.update(td.toString().getBytes());
			sha.update(md.getParamsDesc().getBytes());
			sha.update(md.getThrowsDesc().getBytes());
		}

		byte hashbytes[] = sha.digest();
		long hashlong = 0;
		int i;
		for (i = 0; i < 8; i++) {
			hashlong = hashlong | (hashbytes[i] & 0xff);
			hashlong <<= 8;
		}
		hashval = hashlong;
		return hashlong;
	}

}
