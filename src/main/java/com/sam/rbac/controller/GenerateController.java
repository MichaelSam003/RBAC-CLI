package com.sam.rbac.controller;

import com.sam.rbac.dto.BeanField;
import com.sam.rbac.dto.GenerateDetail;
import com.sam.rbac.dto.GenerateInput;
import com.sam.rbac.service.GenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成接口
 *
 */
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {

	@Autowired
	private GenerateService generateService;

	@ApiOperation("根据表名显示表信息")
	@GetMapping(params = { "tableName" })
	public GenerateDetail generateByTableName(String tableName) {
		GenerateDetail detail = new GenerateDetail();
		detail.setBeanName(generateService.upperFirstChar(tableName));
		List<BeanField> fields = generateService.listBeanField(tableName);
		detail.setFields(fields);

		return detail;
	}

	@ApiOperation("生成代码")
	@PostMapping(value = "/save")
	@ResponseBody
	public void save(@RequestBody GenerateInput input) {
		generateService.saveCode(input);
	}

}
