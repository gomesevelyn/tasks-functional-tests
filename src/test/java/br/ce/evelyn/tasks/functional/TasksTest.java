package br.ce.evelyn.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {


	public WebDriver acessarApliccacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarApliccacao();
		
		try {
			//clicar em add Todo 
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição 
			driver.findElement(By.id("task")).sendKeys("Teste descricao via Selenium");
			
			
			//escrever a data 
			driver.findElement(By.id("dueDate")).sendKeys("25/12/2021");
			
			
			//clicar em salvar 
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", message);
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarApliccacao();
		
		try {
			//clicar em add Todo 
			driver.findElement(By.id("addTodo")).click();
			
			
			//escrever a data 
			driver.findElement(By.id("dueDate")).sendKeys("25/01/2030");
			
			
			//clicar em salvar 
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarApliccacao();
		
		try {
			//clicar em add Todo 
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição 
			driver.findElement(By.id("task")).sendKeys("Teste descricao via Selenium");
			
			//clicar em salvar 
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date!", message);
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarApliccacao();
		
		try {
			//clicar em add Todo 
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição 
			driver.findElement(By.id("task")).sendKeys("Teste descricao via Selenium");
			
			
			//escrever a data 
			driver.findElement(By.id("dueDate")).sendKeys("25/12/2011");
			
			
			//clicar em salvar 
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past!", message);
		}finally {
			driver.quit();
		}
	}
	
}
