package com.westeros;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.westeros.models.DistanceBetweenTwoTownsResult;
import com.westeros.resources.DistanceResource;

@RunWith(SpringRunner.class)
@ComponentScan("com.westeros.resources")
@DataJpaTest	
@SpringBootTest
public class Test8 extends SetupTest{

	@Autowired
	private DistanceResource distanceResource;
	
	
	@Test
    public void test8() throws Exception {
		assertThat(((DistanceBetweenTwoTownsResult)distanceResource.distanceBetweenTwoTowns(1l, "A", "C")).getDistance(), is(9));
		
    }
	
}
