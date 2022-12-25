package com.johnny.boot.openshift.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.johnny.boot.openshift.domain.AccountProto;
import com.johnny.boot.openshift.domain.common.ApplicationConst;
import com.johnny.boot.openshift.domain.model.Account;
import com.johnny.boot.openshift.repository.AccountRepository;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	private Page<Account> accountList;
	private Account account;
	private Integer page = 0;

	@RequestMapping("")
	public String show(
			@RequestParam(value = "page", required = false) String strPage,
			Model model) {
		strPage = (null == strPage || "null".equals(strPage)) ? "0" : strPage;
		page = new Integer(strPage);
		PageRequest pager = new PageRequest(page, ApplicationConst.PAGER_LIMIT,
				Direction.DESC, "id");
		accountList = accountRepository.findAll(pager);
		model.addAttribute("accountList", accountList);
		model.addAttribute("page", page.toString());
		model.addAttribute("hasNextPage", accountList.hasNext());
		return "/account/index";
	}

	@RequestMapping("/test-pro")
	@ResponseBody
	public com.johnny.boot.openshift.domain.AccountProto.Account add(
			HttpServletResponse httpServletResponse) throws IOException {
		return AccountProto.Account
				.newBuilder()
				.setName("fff")
				.setId(12l)
				.setStatus(
						com.johnny.boot.openshift.domain.TypeProtos.Status.DISABLE)
				.setParentId(123l).build();
		// return null;
	}

	public static void main(String[] args) {

		// Builder builder = AccountProto.Account.newBuilder();
		//
		// FieldDescriptor fs = AccountProto.Account.getDescriptor()
		// .findFieldByName(name);
		//
		// com.johnny.boot.openshift.domain.TypeProtos.Status.valueOf(value);
		// // builder.setField(field, value)
		//
		// Map<FieldDescriptor, Object> key = builder.getAllFields();
		//
		// com.johnny.boot.openshift.domain.AccountProto.Account acc =
		// AccountProto.Account
		// .newBuilder()
		//
		// .setName("fff")
		// .setId(12l)
		// .setStatus(
		// com.johnny.boot.openshift.domain.TypeProtos.Status.DISABLE)
		// .setParentId(123l).build();

	}

	/**
	 * show pages
	 */
	@RequestMapping("/after")
	public String showAfter(@RequestParam("page") String strPage, Model model) {
		strPage = (null == strPage || "null".equals(strPage)) ? "0" : strPage;// null文字や空の場合は0とする。
		page = new Integer(strPage) + 1;
		model.addAttribute("page", page.toString());
		return "redirect:/account";
	}

	/**
	 * show pages
	 */
	@RequestMapping("/before")
	public String showBefore(@RequestParam("page") String strPage, Model model) {
		strPage = (null == strPage || "null".equals(strPage)) ? "0" : strPage;// null文字や空の場合は0とする。
		page = new Integer(strPage);
		if (page > 0) {
			page = page - 1;
		}
		model.addAttribute("page", page.toString());
		return "redirect:/account";
	}

	/**
	 * show one
	 */
	@RequestMapping("/{id}")
	public String showById(@PathVariable("id") Long id, Model model) {
		// 全件取得
		account = accountRepository.findOne(id);
		List<Account> list = new ArrayList<Account>();
		list.add(account);
		model.addAttribute("accountList", list);
		return "/account/index";
	}

	/**
	 * create
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String createUpdate(Account account, Model model) {

		account = accountRepository.save(account);
		return show("0", model);
	}

	/**
	 * create(View)
	 */
	@RequestMapping(value = "/create")
	public String create(Account account, Model model) {
		return "/account/edit";
	}

	/**
	 * edit(View)
	 */
	@RequestMapping(value = "/edit/{id}")
	public String edit(@Param("action") String action,
			@PathVariable("id") Long id, Account account, Model model) {

		account = accountRepository.findOne(id);

		// action != deleteの場合は、続きの処理を行わない。
		if ("delete".equals(action)) {
			accountRepository.delete(account);
			return show("0", model);
		}

		model.addAttribute("account", account);
		return "/account/edit";
	}

}