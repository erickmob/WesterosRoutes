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

import com.westeros.resources.DistanceResource;

@RunWith(SpringRunner.class)
@ComponentScan("com.westeros.resources")
@DataJpaTest	
@SpringBootTest
public class Test9 extends SetupTest{

	@Autowired
	private DistanceResource distanceResource;
	
	
	@Test
    public void test6() throws Exception {
		assertThat(distanceResource.distanceBetweenTwoTowns(1l, "B", "B"), is(0));
    }
	
}
