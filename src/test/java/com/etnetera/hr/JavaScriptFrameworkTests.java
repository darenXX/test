package com.etnetera.hr;

import com.etnetera.hr.controller.JavaScriptFrameworkController;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Class used for Spring Boot/MVC based tests.
 * simple test for controller without db and H2 i use for local test H2 test DB
 * assertion basic value
 * possible testing with Mock Mvc more complex testing or with db before /postman, mockon,etc. or json/
 * 
 * @author Etnetera
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkTests {

    @Autowired
    JavaScriptFrameworkRepository repository;

    @Autowired
    JavaScriptFrameworkController controller;


    @Test
    public void testFrameworks() {
        List<String> versionAlfa = Arrays.asList("AB2", "3.0.40", "5.1-SNAPSHOT");
        List<String > versionBeta = Arrays.asList("3.3.3.A", "1.0");
        List<String > versionDelta = Arrays.asList("1.0-2");
        List<JavaScriptFramework> javaScriptFramework = Arrays.asList(
                new JavaScriptFramework(1L,"Alfa", versionAlfa, new Date(2017, 2, 1), 1),
                new JavaScriptFramework( 2L ,"Beta", versionBeta,new Date(2020, 1, 4), 5),
                new JavaScriptFramework(3L,"Delta",versionDelta, new Date(2047, 5, 3) , 10));

        repository.saveAll(javaScriptFramework);
        Assert.assertNotNull(controller.getFrameworks());

    }

    @Test
    public void testFrameworksById() {

        List<String> versionAlfa = Arrays.asList("AB2", "3.0.40", "5.1-SNAPSHOT");
        List<String > versionBeta = Arrays.asList("3.3.3.A", "1.0");
        List<String > versionDelta = Arrays.asList("1.0-2");
        List<JavaScriptFramework> javaScriptFramework = Arrays.asList(
                new JavaScriptFramework(1L,"Alfa", versionAlfa, new Date(2017, 2, 1), 1),
                new JavaScriptFramework( 2L ,"Beta", versionBeta,new Date(2020, 1, 4), 5),
                new JavaScriptFramework(3L,"Delta",versionDelta, new Date(2047, 5, 3) , 10));

        repository.saveAll(javaScriptFramework);

        Assert.assertNotNull(controller.getFrameworksById(2l));

    }

    @Test
    public void testAddNewFramework() {

        List<String> versionAlfa = Arrays.asList("AB2", "3.0.40", "5.1-SNAPSHOT");
        JavaScriptFramework jvs = new JavaScriptFramework(1L,"Alfa", versionAlfa, new Date(2017, 2, 1), 1);

        controller.addNewFramework(jvs);
        Assert.assertNotNull(repository.findById(1L));
        Assert.assertEquals("Alfa",repository.findById(1L).get().getName());
        Assert.assertEquals(1L,repository.findById(1L).get().getHypeLevel().intValue());

    }

    @Test
    public void testUpdateFramework() {
        List<String> versionAlfa = Arrays.asList("AB2", "3.0.40", "5.1-SNAPSHOT");
        JavaScriptFramework jvs = new JavaScriptFramework(1L,"Alfa", versionAlfa, new Date(2017, 2, 1), 1);

        repository.save(jvs);
        Assert.assertNotNull(repository.findById(1L));

        JavaScriptFramework jvsUpdate = new JavaScriptFramework("CHANGE");
        controller.updateFramework(1L, jvsUpdate);

        Assert.assertNotNull(repository.findById(1L));
        Assert.assertNotEquals("Alfa",repository.findById(1L).get().getName());
        Assert.assertEquals("CHANGE",repository.findById(1L).get().getName());
    }

    @Test
    public void testDelete() {
        List<String> versionAlfa = Arrays.asList("AB2", "3.0.40", "5.1-SNAPSHOT");
        JavaScriptFramework jvs = new JavaScriptFramework(1L,"Alfa", versionAlfa, new Date(2017, 2, 1), 1);

        repository.save(jvs);
        Assert.assertNotNull(repository.findById(1L));
        Assert.assertTrue(repository.existsById(1L));

        controller.delete(1L);
        Assert.assertFalse(repository.existsById(1L));
    }



}
