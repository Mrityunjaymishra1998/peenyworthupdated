package com.learning.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;
import com.learning.management.doa.VendorRepository;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.VendorVO;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)

public class VendorServiceImplTest {
	@Autowired
	VendorService vendorService;
	@MockBean
	VendorRepository vendorsRepo;
	
	@BeforeEach
	public void setUp() {
		ArrayList<VendorVO>vendorsList=new ArrayList<VendorVO>();
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Regular");
		vendor.setVendorDesc("BookSeller");
		
		vendorsList.add(vendor);
		VendorRepository usersRepo=mock(VendorRepository.class);
	
		Mockito.when(usersRepo.findById("usr_1")).thenReturn(Optional.of(vendor));
		Mockito.when(usersRepo.findAll()).thenReturn(new ArrayList<VendorVO>());
		Mockito.when(usersRepo.save(vendor)).thenReturn(vendor);
	
	}
	
	@Test
	public void addTest() throws Exception {
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		Mockito.when(vendorsRepo.save(vendor)).thenReturn(vendor);
		vendorService.add(vendor);
		assertEquals(vendor.getVendorId(),vendorService.add(vendor));
	}
	
	@Test
	public void getTest() throws Exception{
		
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		Mockito.when(vendorsRepo.findById("usr_1")).thenReturn(Optional.of(vendor));
		assertEquals(vendor,vendorService.findById("usr_1"));
		assertEquals(vendor.getVendorId(), vendorService.findById("usr_1").getVendorId());
		
	}
	@Test
	public void getExceptionTest() throws Exception {
		
		Mockito.when(vendorsRepo.findById("1")).thenReturn(null);
		
		Assertions.assertThrows( Exception.class, ()->vendorService.findById("1"));
	
	}
	
	
	@Test
	public void updateTest() throws Exception{
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
	
		Mockito.when(vendorsRepo.save(vendor)).thenReturn(vendor);
		VendorVO expectedUser=vendorService.update(vendor);
		assertEquals(vendor,expectedUser );
	}
	
	@Test
	public void deleteTest() throws Exception{
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		Mockito.when(vendorsRepo.findById("1")).thenReturn(Optional.of(vendor));
		assertEquals(null, vendorService.delete("1"));
		
	}
	@Test
	public void delete_Exception_Test() throws Exception{
		Mockito.when(vendorsRepo.findById("1")).thenReturn(null);
		Assertions.assertThrows(Exception.class, ()->vendorService.delete("1"));
		
	}

	
	@Test
	public void addAllTest() throws Exception{
		ArrayList<VendorVO>vendorsList=new ArrayList<VendorVO>();
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		vendorsList.add(vendor);
		
	   vendorService.addAll(vendorsList);

	}
	@Test
	public void findAllTest() throws Exception {
		ArrayList<VendorVO> vendorsList = new ArrayList<VendorVO>();
		VendorVO vendor = new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		vendorsList.add(vendor);

		vendor.setVendorId("2");
		vendor.setVendorName("alice");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("Music");

		vendorsList.add(vendor);
		
		
		Example<VendorVO> vendorVOExample = Example.of(vendor);
		Mockito.when(vendorsRepo.findAll(vendorVOExample)).thenReturn(vendorsList);

		assertEquals(vendorsList, vendorService.findAll(vendor));

}
	
	@Test
	public void addExceptionTest() {
		
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		Mockito.when(vendorsRepo.save(null)).thenReturn(null);
		Assertions.assertThrows(Exception.class, ()->vendorService.add(vendor));
		
	}
	@Test
	public void updateExceptionTest() {
		
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		Mockito.when(vendorsRepo.save(null)).thenReturn(null);
		Assertions.assertThrows(BusinessException.class, ()->vendorService.update(null));
		
	}
	@Test
	public void findAllExceptionTest() {
		
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		Example<VendorVO> vendorVOExample = Example.of(vendor);
		
		Mockito.when(vendorsRepo.findAll(vendorVOExample)).thenThrow(NullPointerException.class);
		BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> vendorService.findAll(vendor));
		assertEquals(NullPointerException.class, ex.getCause().getClass());
		
	}
	}
