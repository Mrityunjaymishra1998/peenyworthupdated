package com.learning.management.exception;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.learning.management.LearningProjectApplication;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LearningProjectApplication.class)
public class BusinessExceptionTest {
	@Test
	public void getterAndSetterCheck() {
		BusinessException ex = new BusinessException();

		ex.setStatus(HttpStatus.OK);
		ex.setMessage("exceptionMessage");
		ex.setDebugMessage("debugException");
		ex.setThrowable(new NullPointerException());

		assertEquals(HttpStatus.OK, ex.getStatus());
		assertEquals("exceptionMessage", ex.getMessage());
		assertEquals("debugException", ex.getDebugMessage());
		assertEquals(NullPointerException.class, ex.getThrowable().getClass());

	}

	@Test
	public void construtorCheck() {
		BusinessException ex = new BusinessException(HttpStatus.OK, "exceptionMessage", "debugException",
				new NullPointerException());

		assertEquals(HttpStatus.OK, ex.getStatus());
		assertEquals("exceptionMessage", ex.getMessage());
		assertEquals("debugException", ex.getDebugMessage());
		assertEquals(NullPointerException.class, ex.getThrowable().getClass());
		assertEquals(NullPointerException.class, ex.getCause().getClass());
	}
}
