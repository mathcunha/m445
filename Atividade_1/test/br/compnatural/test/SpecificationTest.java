package br.compnatural.test;

import java.util.logging.Logger;

import br.compnatural.Specification;
import br.compnatural.State;
import junit.framework.TestCase;

public class SpecificationTest extends TestCase {
	
	private Specification specification = new Specification();
	protected Logger log = Logger.getLogger(SpecificationTest.class.getName());

	public void testInitializeRange_zero_um() {
		log.info(">>Inicio ");
		int min = 0;
		int max = 1; 
		specification.addCoordinate("x", min, max);
		
		 
		
		State state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
	}
	
	public void testInitializeRange_range() {
		log.info(">>Inicio ");
		int min = 0;
		int max = 3;
		specification.addCoordinate("x", min , max);
		
		 
		
		State state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
	}

	public void testInitializeRange_negativo() {
		log.info(">>Inicio ");
		int min = -2;
		int max = 2;
		specification.addCoordinate("x", min , max);
		
		 
		
		State state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
		
		state = specification.initialize();
		assertEquals(1,state.getCoordinates().length);
		assertEquals(true, state.getCoordinates()[0] <= (double) max && state.getCoordinates()[0] >= (double) min);
		
	}

}
