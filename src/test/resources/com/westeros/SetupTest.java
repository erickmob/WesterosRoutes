package com.westeros;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.westeros.models.Graph;
import com.westeros.models.Route;
import com.westeros.repository.GraphRepository;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@ComponentScan("com.westeros.resources")
@DataJpaTest	
@SpringBootTest
public class SetupTest  extends TestCase  {
	
	@Autowired
	private GraphRepository gr;
	
	@Before
    public void setup() throws Exception {
		Graph graphTest = new Graph();
		//AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		graphTest.getData().add(new Route("A","B",5));
		graphTest.getData().add(new Route("B","C",4));
		graphTest.getData().add(new Route("C","D",8));
		graphTest.getData().add(new Route("D","C",8));
		graphTest.getData().add(new Route("D","E",6));
		graphTest.getData().add(new Route("A","D",5));
		graphTest.getData().add(new Route("C","E",2));
		graphTest.getData().add(new Route("E","B",3));
		graphTest.getData().add(new Route("A","E",7));
    	gr.save(graphTest);
    }

	
}
