package Pack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import Pack.service.InventoryService;
import Pack.service.TestService;
import Pack.vo.InventoryCustomer;
import Pack.vo.InventoryItemname;
import Pack.vo.InventorySch;
import Pack.vo.InventoryUpd;
import Pack.vo.InventoryVo;
import Pack.vo.InventoryWarehouse;
import Pack.vo.TestVo;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class MainController {
	@Autowired
	InventoryService inventoryService;
	@Autowired
	TestService testService;

	@GetMapping("/test")
	public List test() {
		System.out.println("test");
	    ModelAndView mav = new ModelAndView("test");

	    List<TestVo> testList = testService.selectTest();
	    System.out.println(testList);
	    mav.addObject("list", testList);

	    return testList;
	}
	
	@GetMapping("/")
	public List inventory() {
		System.out.println("inventorySelect");
	    List<InventoryVo> inventoryList = inventoryService.selectAll();
	    System.out.println(inventoryList.size());
	    return inventoryList;
	}
	
	@GetMapping("/search")
	public List inventorySch(InventorySch inventorySch) {
		System.out.println("inventorySearch");
		System.out.println(inventorySch);
		List<InventoryVo> inventorySearch = inventoryService.selectSome(inventorySch);
		System.out.println(inventorySearch.size());
		return inventorySearch;
	}
	
	@GetMapping("/{warehouseCode}")
	public List inventoryWarehouse(@PathVariable("warehouseCode") String warehouseCode) {
		System.out.println("inventoryWarehouse");
		List<InventoryWarehouse> inventoryWarehouse = inventoryService.selectWarehouse(warehouseCode);
		System.out.println(inventoryWarehouse.size());
		return inventoryWarehouse;
	}
	
	@GetMapping("/inventory/{location}")
	public List inventoryItemname(@PathVariable("location") String location) {
		System.out.println("inventoryItemnameLocation");
		List<InventoryItemname> inventoryItemname = inventoryService.selectItemname(location);
		System.out.println(inventoryItemname.size());
		return inventoryItemname;
	}
	
	@GetMapping("/inventory/customer/{location}")
	public List inventoryCustomer(@PathVariable("location") String location) {
		System.out.println("inventoryCustomerLocation");
		List<InventoryCustomer> inventoryCustomer = inventoryService.selectCustomer(location);
		System.out.println(inventoryCustomer.size());
		return inventoryCustomer;
	}
	
	@GetMapping("/map")	
	public HashMap<String, Object> selectToMap() {
		System.out.println("map data 요청");
		HashMap result = inventoryService.selectToMap();
		return result;
	}
	
	@DeleteMapping("/{lotNo}")
	public boolean warehouseDel(@PathVariable("lotNo") String lotNo) {
		System.out.println(lotNo);
		int result = inventoryService.inventoryDel(lotNo);
		return result == 1 ? true : false;
	}
	
	@PutMapping("/{lotNo}")
	public boolean warehouseUpd(@PathVariable("lotNo") String lotNo, @RequestBody InventoryUpd inventoryUpd) {
		System.out.println(lotNo);
		System.out.println(inventoryUpd);
		int result = inventoryService.inventoryUpd(lotNo, inventoryUpd);
		return result == 1 ? true : false;
	}
}
