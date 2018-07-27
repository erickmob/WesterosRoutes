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

import com.westeros.models.PathNodes;
import com.westeros.resources.DistanceResource;

@RunWith(SpringRunner.class)
@ComponentScan("com.westeros.resources")
@DataJpaTest	
@SpringBootTest
public class Test4  extends SetupTest{
	
	
	@Autowired
	private DistanceResource distanceRecource;
	
	
	@Test
    public void test4() throws Exception {
		PathNodes pathNodes = new PathNodes();
		pathNodes.setPath(new String[]{"A","E","B","C", "D"});
		assertThat(distanceRecource.distanceForPath(1l, pathNodes), is(22));
    }
}
