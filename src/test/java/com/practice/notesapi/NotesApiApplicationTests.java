package com.practice.notesapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NotesApiApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
    void thisShouldFail() {
         assertEquals(1, 2);
    }

}
