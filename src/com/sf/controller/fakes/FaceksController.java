package com.sf.controller.fakes;

import com.jfinal.core.Controller;
import com.sf.service.faceks.FaceksService;

public class FaceksController extends Controller{
	public void index(){
//		System.out.println("Filter List Page");
//		FaceksService.filterFaceks();
//		
//		System.out.println("Filter img Page");
//		FaceksService.filterImg();
//		FaceksService.download();
		renderText("success");
	}
	public void filterFaceks(){
		System.out.println("Filter List Page");
		FaceksService.filterFaceks();
		renderText("success");
	}
	public void filterImg(){
		System.out.println("Filter img Page");
		FaceksService.filterImg();
		renderText("success");
	}
	public void download(){
		FaceksService.download();
		renderText("success");
	}
	
}
