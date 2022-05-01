package com.udacity.jwdnd.c1.review;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {

	@LocalServerPort
	private Integer port;

	private static WebDriver driver;
	private ChatPage chatInteraction;

	@AfterAll
	public static void afterAll() {
		driver.quit();
	}

	@BeforeEach
	public void beforeEach() {
		driver.get("http://localhost:" + port + "/chat");
		chatInteraction = new ChatPage(driver);
	}

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	void registerAndLoginAndFirstMessage() {
		String inputUsername = "jhont285";
		chatInteraction.registerUser(inputUsername);
		chatInteraction.loginUser(inputUsername);
		var message = "Hi everyone";
		var expectedMessage = List.of(inputUsername, message.toUpperCase());
		var actualMessage = chatInteraction.writeAndGetMessages(message, "Shout");

		assertEquals(expectedMessage, actualMessage);
	}

}
