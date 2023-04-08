package com.doug.concurrencyproject.appmain;

import java.io.IOException;

import com.doug.concurrencyproject.util.ApplicationMenu;

/**
 * 
 * @author Douglas Santos - 2020338
 *
 */

public class ApplicationMain {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("||| Welcome to CA1: Application of Concurrency to Common Tasks |||\n"
				+ "__________________________________________________________________");

		ApplicationMenu menu = new ApplicationMenu();
		menu.choose();

	}

}
