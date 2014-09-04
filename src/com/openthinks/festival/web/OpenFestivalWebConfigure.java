package com.openthinks.festival.web;

import com.openthinks.easyweb.annotation.configure.EasyConfigure;
import com.openthinks.easyweb.annotation.configure.RequestSuffixs;
import com.openthinks.easyweb.annotation.configure.ScanPackages;

/**
 * 
 * @author minjdai
 *
 */
@EasyConfigure
@ScanPackages({"com.openthinks.festival.web.controller"})
@RequestSuffixs("")
public class OpenFestivalWebConfigure {

}
