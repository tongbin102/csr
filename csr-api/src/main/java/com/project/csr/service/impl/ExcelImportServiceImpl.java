package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.csr.constants.CsrConstant;
import com.project.csr.constants.DictionaryType;
import com.project.csr.excel.DefaultExcelListener;
import com.project.csr.excel.EasyExcelUtils;
import com.project.csr.model.po.*;
import com.project.csr.model.vo.*;
import com.project.csr.service.*;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.utils.ToolsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: bin.tong
 * @date: 2020/11/24 9:53
 **/
@Slf4j
@Service
public class ExcelImportServiceImpl implements ExcelImportService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Autowired
	private UserStoreService userStoreService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private FactorService factorService;

	@Autowired
	private ElementService elementService;

	@Autowired
	private RegulationService regulationService;

	@Autowired
	private QuestionSurveyService questionSurveyService;

	@Autowired
	private QuestionMonitorService questionMonitorService;

	@Autowired
	private QuestionAssistanceService questionAssistanceService;

	@Autowired
	private QuestionRescueService questionRescueService;

	@Autowired
	private QuestionDataService questionDataService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private ScoreFactorService scoreFactorService;

	@Autowired
	private ScoreChannelService scoreChannelService;

	@Autowired
	private ScoreQuestionService scoreQuestionService;

	@Autowired
	private RegulationScoreService regulationScoreService;

	@Autowired
	private RegulationScoreChannelService regulationScoreChannelService;

	@Autowired
	private QuestionComplainService questionComplainService;

	@Override
	public void importDefaultSettings(MultipartFile file) throws IOException {
		List<ChannelPo> channelPoList = channelService.list();
		// 缺省配置
		DefaultExcelListener<T> defaultSettingsImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), defaultSettingsImportListener, DefaultSettingsImportVo.class, 8, 1);
		List<DefaultSettingsImportVo> defaultSettingsImportVoList = ConvertUtils.convert(defaultSettingsImportListener.getRows(), DefaultSettingsImportVo.class);

		defaultSettingsImportVoList.forEach(defaultSettingsImportVo -> {
			channelPoList
					.stream()
					.filter(channelPo -> channelPo.getName().equals(defaultSettingsImportVo.getChannelName()))
					.findFirst().ifPresent(channelPo -> {
				if ("是".equals(defaultSettingsImportVo.getEvaluateType()) && !DictionaryType.CHANNEL_TYPE_EVALUATE.equals(channelPo.getCtype())) {
					channelPo.setCtype(DictionaryType.CHANNEL_TYPE_EVALUATE);
					channelService.updateById(channelPo);
				} else if("否".equals(defaultSettingsImportVo.getEvaluateType()) && !DictionaryType.CHANNEL_TYPE_DEDUCT.equals(channelPo.getCtype())) {
					channelPo.setCtype(DictionaryType.CHANNEL_TYPE_DEDUCT);
					channelService.updateById(channelPo);
				}
			});
		});
	}

	@Override
	public void importUsersAndStores(MultipartFile file) throws IOException {
		// 先导入区域、店，再导入用户
		DefaultExcelListener<T> storeImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), storeImportListener, StoreImportVo.class, 1, 1);
		List<StoreImportVo> storeImportVoList = ConvertUtils.convert(storeImportListener.getRows(), StoreImportVo.class);
		// 导入大区Regions
		List<RegionPo> regionPoList = new ArrayList<>();
		storeImportVoList
				.stream()
				.map(StoreImportVo::getRegion)
				.distinct()
				.forEach(strRegion -> {
					RegionPo regionPo = new RegionPo();
					regionPo.setCode(strRegion);
					regionPo.setName(strRegion);
					regionPo.setNationalCode("national");
					regionPoList.add(regionPo);
				});
		regionService.deleteAll();
		regionService.saveBatch(regionPoList);

		// 导入省份Province
		Set<ProvincePo> provincePoSet = new HashSet<>();
		storeImportVoList
				// .stream()
				// .map(StoreImportVo::getProvince)
				// .distinct()
				.forEach(storeImportVo -> {
					ProvincePo provincePo = new ProvincePo();
					provincePo.setCode(storeImportVo.getProvince());
					provincePo.setName(storeImportVo.getProvince());
					provincePo.setRegionCode(storeImportVo.getRegion());
					provincePoSet.add(provincePo);
				});
		List<ProvincePo> provincePoList = new ArrayList<>(provincePoSet);
		provinceService.deleteAll();
		provinceService.saveBatch(provincePoList);

		// 导入城市City
		Set<CityPo> cityPoSet = new HashSet<>();
		storeImportVoList
				.forEach(storeImportVo -> {
					// CityPo cityPo = new CityPo();
					// cityPo.setCode(storeImportVo.getCity());
					// cityPo.setName(storeImportVo.getCity());
					// cityPo.setProvinceCode(storeImportVo.getProvince());
					// cityPoSet.add(cityPo);

					CityPo superiorCityPo = new CityPo();
					superiorCityPo.setCode(storeImportVo.getSuperiorCity());
					superiorCityPo.setName(storeImportVo.getSuperiorCity());
					superiorCityPo.setProvinceCode(storeImportVo.getProvince());
					superiorCityPo.setRegionCode(storeImportVo.getRegion());
					cityPoSet.add(superiorCityPo);
				});
		List<CityPo> cityPoList = new ArrayList<>(cityPoSet);
		cityService.deleteAll();
		cityService.saveBatch(cityPoList);

		// 导入一级店及经销店
		Set<StorePo> superiorPoSet = new HashSet<>();
		storeImportVoList
				.forEach(storeImportVo -> {
					StorePo superiorPo = new StorePo();
					superiorPo.setCode(storeImportVo.getSuperiorCode());
					superiorPo.setName(storeImportVo.getSuperiorName());
					superiorPo.setCityCode(storeImportVo.getSuperiorCity());
					superiorPo.setRegionCode(storeImportVo.getRegion());
					superiorPoSet.add(superiorPo);
				});
		List<StorePo> superiorPoList = new ArrayList<>(superiorPoSet);
		storeService.deleteAll();
		storeService.saveBatch(superiorPoList);

		Set<StorePo> storePoSet = new HashSet<>();
		storeImportVoList
				.forEach(storeImportVo -> {
					StorePo storePo = new StorePo();
					storePo.setCode(storeImportVo.getCode());
					storePo.setName(storeImportVo.getName());
					storePo.setScale(storeImportVo.getScale());
					storePo.setCityCode(storeImportVo.getCity());
					storePo.setParentCode(storeImportVo.getSuperiorCode());
					storePo.setRegionCode(storeImportVo.getRegion());
					storePoSet.add(storePo);
				});
		List<StorePo> storePoList = new ArrayList<>(storePoSet);
		storeService.saveBatch(storePoList);

		// 导入用户Users
		// 同时导入一级 / 二级店的用户与所辖店关系
		DefaultExcelListener<T> userImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), userImportListener, UserImportVo.class, 0, 1);
		List<UserImportVo> userImportVoList = ConvertUtils.convert(userImportListener.getRows(), UserImportVo.class);
		String defaultPassword = encoder.encode(CsrConstant.DEFAULT_RAW_PASSWORD);

		List<UserPo> existUserPoList = userService.list();
		// 新增用户列表
		List<UserPo> userPoList = new ArrayList<>();
		// 更新用户列表
		List<UserPo> updateUserPoList = new ArrayList<>();

		userImportVoList.forEach(userImportVo -> {
			if(null == userImportVo.getEmail()){
				userImportVo.setEmail("");
			}
			List<UserPo> list = existUserPoList.stream().filter(userPo -> userPo.getUsername().equals(userImportVo.getUsername())).collect(Collectors.toList());
			if (list.size() == 0) {
				// 不存在该用户
				UserPo userPo = new UserPo();
				userPo.setUsername(userImportVo.getUsername());
				userPo.setName(userImportVo.getName());
				userPo.setPassword(defaultPassword);
				userPo.setEmail(userImportVo.getEmail());
				userPo.setChangeFlag(DictionaryType.CHANGE_PASSWORD_NEED);
				// 如果用户名称与权限码相等
				String roleCode = userImportVo.getRuleCode().trim();
				if ("ADM".equals(roleCode.trim())) {
					// 系统管理员
					userPo.setRoleId(DictionaryType.ROLE_ID_ADMIN);
				} else if ("OEM".equals(roleCode.trim())) {
					// 厂家账户
					userPo.setRoleId(DictionaryType.ROLE_ID_NATIONAL);
					// userPo.setRef(DictionaryType.ROLE_NAME_NATIONAL);
				} else {
					if (null == userPo.getRoleId()) {
						// 大区账户
						storeImportVoList.stream().filter(storeImportVo -> storeImportVo.getRegionManager().trim().equals(roleCode)).findFirst().ifPresent(storeImportVo -> {
							userPo.setRoleId(DictionaryType.ROLE_ID_REGION);
							userPo.setRef(storeImportVo.getRegion());
						});
					}
					if (null == userPo.getRoleId()) {
						// 区域用户
						storeImportVoList.stream().filter(storeImportVo -> storeImportVo.getAreaManager().trim().equals(roleCode)).findFirst().ifPresent(storeImportVo -> {
							userPo.setRoleId(DictionaryType.ROLE_ID_AREA);
							userPo.setRef(storeImportVo.getRegion());
							// userPo.setRef(storeImportVo.getAreaManager());
						});
					}
					if (null == userPo.getRoleId()) {
						// 一级店账户
						storeImportVoList.stream().filter(storeImportVo -> storeImportVo.getSuperiorCode().trim().equals(roleCode)).findFirst().ifPresent(storeImportVo -> {
							userPo.setRoleId(DictionaryType.ROLE_ID_SUPERIOR);
							userPo.setRef(storeImportVo.getSuperiorCode());
						});
					}
					if (null == userPo.getRoleId()) {
						// 二级店账户
						storeImportVoList.stream().filter(storeImportVo -> storeImportVo.getCode().trim().equals(roleCode)).findFirst().ifPresent(storeImportVo -> {
							userPo.setRoleId(DictionaryType.ROLE_ID_STORE);
							userPo.setRef(storeImportVo.getCode());
						});
					}
				}
				userPoList.add(userPo);
			} else {
				// 用户名已存在则更新
				UserPo userPo = list.get(0);
				if(null == userPo.getEmail()){
					userPo.setEmail("");
				}
				if (!userPo.getUsername().equals(userImportVo.getName()) && !userPo.getEmail().equals(userImportVo.getEmail())) {
					userPo.setUsername(userImportVo.getName());
					userPo.setEmail(userImportVo.getEmail());
					updateUserPoList.add(userPo);
				}
			}
		});
		// userService.deleteUsersExceptAdmin();
		if (userPoList != null && userPoList.size() > 0) {
			userService.saveBatch(userPoList);
		}
		if (updateUserPoList != null && updateUserPoList.size() > 0) {
			userService.updateBatchById(updateUserPoList);
		}

		// 导入用户与区域/城市关系
		Set<UserStorePo> userStorePoSet = new HashSet<>();
		storeImportVoList.forEach(storeImportVo -> {
			String storeCode = storeImportVo.getCode();
			String superiorCode = storeImportVo.getSuperiorCode();
			String cityCode = storeImportVo.getSuperiorCity();
			String provinceCode = storeImportVo.getProvince();

			UserStorePo userStorePo = new UserStorePo();
			userStorePo.setUserCode(storeImportVo.getCode());
			userStorePo.setStoreCode(storeCode);
			userStorePo.setSuperiorCode(superiorCode);
			userStorePo.setCityCode(cityCode);
			userStorePo.setProvinceCode(provinceCode);
			userStorePoSet.add(userStorePo);
			UserStorePo userSuperiorPo = new UserStorePo();
			userSuperiorPo.setUserCode(storeImportVo.getSuperiorCode());
			userSuperiorPo.setStoreCode(storeCode);
			userSuperiorPo.setSuperiorCode(superiorCode);
			userSuperiorPo.setCityCode(cityCode);
			userSuperiorPo.setProvinceCode(provinceCode);
			userStorePoSet.add(userSuperiorPo);
			UserStorePo userAreaPo = new UserStorePo();
			userPoList.stream().filter(userPo -> userPo.getName().equals(storeImportVo.getAreaManager().trim())).findFirst().ifPresent(userPo -> userAreaPo.setUserCode(userPo.getUsername()));
			userAreaPo.setStoreCode(storeCode);
			userAreaPo.setSuperiorCode(superiorCode);
			userAreaPo.setCityCode(cityCode);
			userAreaPo.setProvinceCode(provinceCode);
			userStorePoSet.add(userAreaPo);
			UserStorePo userRegionPo = new UserStorePo();
			userPoList.stream().filter(userPo -> userPo.getName().equals(storeImportVo.getRegionManager().trim())).findFirst().ifPresent(userPo -> userRegionPo.setUserCode(userPo.getUsername()));
			userRegionPo.setStoreCode(storeCode);
			userRegionPo.setSuperiorCode(superiorCode);
			userRegionPo.setCityCode(cityCode);
			userRegionPo.setProvinceCode(provinceCode);
			userStorePoSet.add(userRegionPo);
		});
		List<UserStorePo> userStorePoList = new ArrayList<>(userStorePoSet);
		userStoreService.deleteAll();
		userStoreService.saveBatch(userStorePoList);
	}

	@Override
	public void importRegulationsAndQuestions(MultipartFile file) throws IOException {
		DefaultExcelListener<T> regulationImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), regulationImportListener, RegulationImportVo.class, 2, 1);
		List<RegulationImportVo> regulationImportVoList = ConvertUtils.convert(regulationImportListener.getRows(), RegulationImportVo.class);

		// 导入因子
		// Set<FactorPo> factorPoSet = new HashSet<>();
		// regulationImportVoList.forEach(regulationImportVo -> {
		//     FactorPo factorPo = new FactorPo();
		//     factorPo.setCode(regulationImportVo.getFactorName().trim());
		//     factorPo.setName(regulationImportVo.getFactorName().trim());
		//     factorPoSet.add(factorPo);
		// });
		// List<FactorPo> factorPoList = new ArrayList<>(factorPoSet);
		// factorService.deleteAll();
		// factorService.saveBatch(factorPoList);
		// 导入要素
		Set<ElementPo> elementPoSet = new HashSet<>();
		regulationImportVoList.forEach(regulationImportVo -> {
			ElementPo elementPo = new ElementPo();
			elementPo.setFactorCode(regulationImportVo.getFactorName().trim());
//            elementPo.setCode(regulationImportVo.getElementName().trim());
			elementPo.setCode(regulationImportVo.getFactorName().trim() + ";" + regulationImportVo.getElementName().trim());
			elementPo.setName(regulationImportVo.getFactorName().trim() + ";" + regulationImportVo.getElementName().trim());
			elementPoSet.add(elementPo);
		});
		List<ElementPo> elementPoList = new ArrayList<>(elementPoSet);
		elementService.deleteAll();
		elementService.saveBatch(elementPoList);
		// 导入细则
		Set<RegulationPo> regulationPoSet = new HashSet<>();
		regulationImportVoList.forEach(regulationImportVo -> {
			RegulationPo regulationPo = new RegulationPo();
			regulationPo.setElementCode(regulationImportVo.getFactorName().trim() + ";" + regulationImportVo.getElementName().trim());
			// regulationPo.setDescription(regulationImportVo.getFactorName().trim() + ";" + regulationImportVo.getElementName().trim() + ";" + regulationImportVo.getDescription().replace("\n", "<br/>").trim());
			regulationPo.setDescription(regulationImportVo.getDescription().replace("\n", "<br/>").trim());
			regulationPo.setScoreType(regulationImportVo.getScoreType().trim());
			regulationPoSet.add(regulationPo);
		});
		List<RegulationPo> regulationPoList = new ArrayList<>(regulationPoSet);
		regulationService.deleteAll();
		regulationService.saveBatch(regulationPoList);

		// 导入调研问卷评分规则
		DefaultExcelListener<T> questionSurveyImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), questionSurveyImportListener, QuestionSurveyImportVo.class, 3, 1);
		List<QuestionSurveyImportVo> questionSurveyImportVoList = ConvertUtils.convert(questionSurveyImportListener.getRows(), QuestionSurveyImportVo.class);
		Set<QuestionSurveyPo> questionSurveyPoSet = new HashSet<>();
		questionSurveyImportVoList.forEach(questionSurveyImportVo -> {
			// regulationImportVoList.stream().filter(regulationImportVo -> regulationImportVo.getQuestionSeriesNo().equals(questionSurveyImportVo.getSeriesNo())).findFirst().ifPresent(regulationImportVo -> questionSurveyPo.setRegulationDescription(regulationImportVo.getFactorName().trim() + ";" + regulationImportVo.getElementName().trim() + ";" + regulationImportVo.getDescription().replace("\n", "<br/>").trim()));
			regulationImportVoList
					.stream()
					.filter(regulationImportVo -> regulationImportVo.getQuestionSeriesNo().equals(questionSurveyImportVo.getSeriesNo()))
					.forEach(regulationImportVo -> {
						QuestionSurveyPo questionSurveyPo = new QuestionSurveyPo();
						questionSurveyPo.setSeriesNo(questionSurveyImportVo.getSeriesNo());
						questionSurveyPo.setRegulationDescription(regulationImportVo.getFactorName() + ";" + regulationImportVo.getElementName() + ";" + regulationImportVo.getDescription().replace("\n", "<br/>"));
						questionSurveyPo.setDescription(questionSurveyImportVo.getDescription().replace("\n", "<br/>"));
						questionSurveyPo.setAnswer1(questionSurveyImportVo.getAnswer1());
						questionSurveyPo.setAnswer2(questionSurveyImportVo.getAnswer2());
						questionSurveyPo.setAnswer3(questionSurveyImportVo.getAnswer3());
						questionSurveyPo.setAnswer4(questionSurveyImportVo.getAnswer4());
						questionSurveyPo.setAnswer5(questionSurveyImportVo.getAnswer5());
						questionSurveyPo.setScoreItem(questionSurveyImportVo.getScoreItem());
						questionSurveyPo.setFormula(questionSurveyImportVo.getFormula());
						questionSurveyPo.setExcellent(questionSurveyImportVo.getExcellent());
						questionSurveyPo.setGood(questionSurveyImportVo.getGood());
						questionSurveyPo.setStandard(questionSurveyImportVo.getStandard());
						questionSurveyPo.setWeak(questionSurveyImportVo.getWeak());
						questionSurveyPoSet.add(questionSurveyPo);
					});
		});
		List<QuestionSurveyPo> questionSurveyVoList = new ArrayList<>(questionSurveyPoSet);
		questionSurveyService.deleteAll();
		questionSurveyService.saveBatch(questionSurveyVoList);
		// 导入过程监控评分规则
		DefaultExcelListener<T> questionMonitorImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), questionMonitorImportListener, QuestionMonitorImportVo.class, 4, 1);
		List<QuestionMonitorImportVo> questionMonitorImportVoList = ConvertUtils.convert(questionMonitorImportListener.getRows(), QuestionMonitorImportVo.class);
		Set<QuestionMonitorPo> questionMonitorPoSet = new HashSet<>();
		questionMonitorImportVoList.forEach(questionMonitorImportVo -> {
			regulationImportVoList
					.stream()
					.filter(regulationImportVo -> regulationImportVo.getQuestionSeriesNo().equals(questionMonitorImportVo.getSeriesNo()))
					.forEach(regulationImportVo -> {
						QuestionMonitorPo questionMonitorPo = new QuestionMonitorPo();
						questionMonitorPo.setSeriesNo(questionMonitorImportVo.getSeriesNo());
						questionMonitorPo.setRegulationDescription(regulationImportVo.getFactorName() + ";" + regulationImportVo.getElementName() + ";" + regulationImportVo.getDescription().replace("\n", "<br/>"));
						questionMonitorPo.setSuggestion(questionMonitorImportVo.getSuggestion().replace("\n", "<br/>"));
						questionMonitorPo.setDescription(questionMonitorImportVo.getDescription().replace("\n", "<br/>"));
						questionMonitorPo.setExcellent(questionMonitorImportVo.getExcellent().replace("\n", "<br/>"));
						questionMonitorPo.setGood(questionMonitorImportVo.getGood().replace("\n", "<br/>"));
						questionMonitorPo.setStandard(questionMonitorImportVo.getStandard().replace("\n", "<br/>"));
						questionMonitorPo.setWeak(questionMonitorImportVo.getWeak().replace("\n", "<br/>"));
						questionMonitorPoSet.add(questionMonitorPo);
					});
		});
		List<QuestionMonitorPo> questionMonitorVoList = new ArrayList<>(questionMonitorPoSet);
		questionMonitorService.deleteAll();
		questionMonitorService.saveBatch(questionMonitorVoList);
		// 导入服务助手评分规则
		DefaultExcelListener<T> questionAssistanceImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), questionAssistanceImportListener, QuestionAssistanceImportVo.class, 5, 1);
		List<QuestionAssistanceImportVo> questionAssistanceImportVoList = ConvertUtils.convert(questionAssistanceImportListener.getRows(), QuestionAssistanceImportVo.class);
		Set<QuestionAssistancePo> questionAssistancePoSet = new HashSet<>();
		questionAssistanceImportVoList.forEach(questionAssistanceImportVo -> {
			regulationImportVoList
					.stream()
					.filter(regulationImportVo -> regulationImportVo.getQuestionSeriesNo().equals(questionAssistanceImportVo.getSeriesNo()))
					.forEach(regulationImportVo -> {
						QuestionAssistancePo questionAssistancePo = new QuestionAssistancePo();
						questionAssistancePo.setSeriesNo(questionAssistanceImportVo.getSeriesNo());
						questionAssistancePo.setRegulationDescription(regulationImportVo.getFactorName() + ";" + regulationImportVo.getElementName() + ";" + regulationImportVo.getDescription().replace("\n", "<br/>"));
						questionAssistancePo.setAnalysisPoint(questionAssistanceImportVo.getAnalysisPoint().replace("\n", "<br/>"));
						questionAssistancePo.setKpi(questionAssistanceImportVo.getKpi().replace("\n", "<br/>"));
						questionAssistancePo.setKpiDescription(questionAssistanceImportVo.getKpiDescription().replace("\n", "<br/>"));
						questionAssistancePo.setExcellent(questionAssistanceImportVo.getExcellent());
						questionAssistancePo.setGood(questionAssistanceImportVo.getGood());
						questionAssistancePo.setStandard(questionAssistanceImportVo.getStandard());
						questionAssistancePo.setWeak(questionAssistanceImportVo.getWeak());
						questionAssistancePoSet.add(questionAssistancePo);
					});
		});
		List<QuestionAssistancePo> questionAssistanceVoList = new ArrayList<>(questionAssistancePoSet);
		questionAssistanceService.deleteAll();
		questionAssistanceService.saveBatch(questionAssistanceVoList);
		// 导入道路救援扣分规则
		DefaultExcelListener<T> questionRescueImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), questionRescueImportListener, QuestionRescueImportVo.class, 6, 1);
		List<QuestionRescueImportVo> questionRescueImportVoList = ConvertUtils.convert(questionRescueImportListener.getRows(), QuestionRescueImportVo.class);
		Set<QuestionRescuePo> questionRescuePoSet = new HashSet<>();
		questionRescueImportVoList.forEach(questionRescueImportVo -> {
			regulationImportVoList
					.stream()
					.filter(regulationImportVo -> regulationImportVo.getQuestionSeriesNo().equals(questionRescueImportVo.getSeriesNo()))
					.forEach(regulationImportVo -> {
						QuestionRescuePo questionRescuePo = new QuestionRescuePo();
						questionRescuePo.setSeriesNo(questionRescueImportVo.getSeriesNo());
						questionRescuePo.setRegulationDescription(regulationImportVo.getFactorName() + ";" + regulationImportVo.getElementName() + ";" + regulationImportVo.getDescription().replace("\n", "<br/>"));
						questionRescuePo.setAnalysisPoint(questionRescueImportVo.getAnalysisPoint().replace("\n", "<br/>"));
						questionRescuePo.setKpi(questionRescueImportVo.getKpi().replace("\n", "<br/>"));
						questionRescuePo.setKpiDescription(questionRescueImportVo.getKpiDescription().replace("\n", "<br/>"));
						questionRescuePo.setDeduct(questionRescueImportVo.getDeduct());
						questionRescuePoSet.add(questionRescuePo);
					});
		});
		List<QuestionRescuePo> questionRescueVoList = new ArrayList<>(questionRescuePoSet);
		questionRescueService.deleteAll();
		questionRescueService.saveBatch(questionRescueVoList);
		// 导入数据准确性扣分规则
		DefaultExcelListener<T> questionDataImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), questionDataImportListener, QuestionDataImportVo.class, 7, 1);
		List<QuestionDataImportVo> questionDataImportVoList = ConvertUtils.convert(questionDataImportListener.getRows(), QuestionDataImportVo.class);
		Set<QuestionDataPo> questionDataPoSet = new HashSet<>();
		questionDataImportVoList.forEach(questionDataImportVo -> {
			regulationImportVoList
					.stream()
					.filter(regulationImportVo -> regulationImportVo.getQuestionSeriesNo().equals(questionDataImportVo.getSeriesNo()))
					.forEach(regulationImportVo -> {
						QuestionDataPo questionDataPo = new QuestionDataPo();
						questionDataPo.setSeriesNo(questionDataImportVo.getSeriesNo());
						questionDataPo.setRegulationDescription(regulationImportVo.getFactorName() + ";" + regulationImportVo.getElementName() + ";" + regulationImportVo.getDescription().replace("\n", "<br/>"));
						questionDataPo.setAnalysisPoint(questionDataImportVo.getAnalysisPoint().replace("\n", "<br/>"));
						questionDataPo.setKpi(questionDataImportVo.getKpi().replace("\n", "<br/>"));
						questionDataPo.setKpiDescription(questionDataImportVo.getKpiDescription().replace("\n", "<br/>"));
						questionDataPo.setDeduct(questionDataImportVo.getDeduct());
						questionDataPoSet.add(questionDataPo);
					});
		});
		List<QuestionDataPo> questionDataVoList = new ArrayList<>(questionDataPoSet);
		questionDataService.deleteAll();
		questionDataService.saveBatch(questionDataVoList);

	}

	@Override
	public void importScoresFactor(MultipartFile file, String period) throws IOException {

		List<ProvincePo> provincePoList = provinceService.list();
		List<CityPo> cityPoList = cityService.list();
		List<StorePo> storePoList = storeService.list();

		DefaultExcelListener<T> scoreFactorImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), scoreFactorImportListener, ScoreFactorImportVo.class, 0, 1);
		List<ScoreFactorImportVo> scoreFactorImportVoList = ConvertUtils.convert(scoreFactorImportListener.getRows(), ScoreFactorImportVo.class);
		Set<ScorePo> scorePoSet = new HashSet<>();
		Set<ScoreFactorPo> scoreFactorPoSet = new HashSet<>();
		scoreFactorImportVoList.forEach(scoreFactorImportVo -> {
			if ("总计".equals(scoreFactorImportVo.getFactorName().trim())) {
				ScorePo scorePo = new ScorePo();
				// scorePo.setPeriod(scoreFactorImportVo.getPeriod());
				scorePo.setPeriod(period);
				Long scopeId = getScopeIdFromName(scoreFactorImportVo.getScopeName());
				scoreFactorImportVo.setScopeId(scopeId);
				if (null != scopeId) {
					String storeCode = scoreFactorImportVo.getStoreCode().trim();
					String regionCode = ToolsUtils.getRegionCode(scopeId, storeCode, provincePoList, cityPoList, storePoList);
					scoreFactorImportVo.setRegionCode(regionCode);
					scorePo.setScopeId(scopeId);
					scorePo.setStoreCode(scoreFactorImportVo.getStoreCode().trim());
					scorePo.setScore(scoreFactorImportVo.getScore().trim());
					scorePoSet.add(scorePo);
				}
			} else {
				ScoreFactorPo scoreFactorPo = new ScoreFactorPo();
				// scoreFactorPo.setPeriod(scoreFactorImportVo.getPeriod());
				scoreFactorPo.setPeriod(period);
				Long scopeId = getScopeIdFromName(scoreFactorImportVo.getScopeName());
				scoreFactorImportVo.setScopeId(scopeId);
				if (null != scopeId) {
					String storeCode = scoreFactorImportVo.getStoreCode().trim();
					String regionCode = ToolsUtils.getRegionCode(scopeId, storeCode, provincePoList, cityPoList, storePoList);
					scoreFactorImportVo.setRegionCode(regionCode);
					scoreFactorPo.setScopeId(scopeId);
					scoreFactorPo.setStoreCode(scoreFactorImportVo.getStoreCode().trim());
					scoreFactorPo.setFactorCode(scoreFactorImportVo.getFactorName().trim());
					scoreFactorPo.setScore(scoreFactorImportVo.getScore().trim());
					scoreFactorPoSet.add(scoreFactorPo);
				}
			}
		});
		//计算全国排名 & 区域排名
		List<ScorePo> scorePoList = new ArrayList<>(scorePoSet);
		scorePoList.forEach(scorePo -> {
			int score = StringUtils.isEmpty(scorePo.getScore()) ? 0 : Integer.parseInt(scorePo.getScore());
			// 全国排名
			long countCountry = scoreFactorImportVoList
					.stream()
					// .filter(vo -> vo.getPeriod().equals(scorePo.getPeriod())
					.filter(vo -> "总计".equals(vo.getFactorName())
							&& period.equals(scorePo.getPeriod())
							&& vo.getScopeId().equals(scorePo.getScopeId())
							&& !vo.getStoreCode().equals(scorePo.getStoreCode())
							&& (StringUtils.isEmpty(vo.getScore()) ? 0 : Integer.parseInt(vo.getScore())) > score).count();
			scorePo.setRankCountry((int) countCountry + 1);
			// 区域排名
			Long scopeId = scorePo.getScopeId();
			String storeCode = scorePo.getStoreCode();
			String regionCode = ToolsUtils.getRegionCode(scopeId, storeCode, provincePoList, cityPoList, storePoList);
			long countScope = scoreFactorImportVoList
					.stream()
					// .filter(vo -> vo.getPeriod().equals(scorePo.getPeriod())
					.filter(vo -> period.equals(scorePo.getPeriod())
							&& vo.getScopeId().equals(scorePo.getScopeId())
							&& StringUtils.isNotBlank(vo.getRegionCode())
							&& vo.getRegionCode().equals(regionCode)
							&& !vo.getStoreCode().equals(scorePo.getStoreCode())
							&& (StringUtils.isEmpty(vo.getScore()) ? 0 : Integer.parseInt(vo.getScore())) > score).count();
			scorePo.setRankScope((int) countScope + 1);
		});
		scoreService.deleteByPeriod(period);
		scoreService.saveBatch(scorePoList);
		List<ScoreFactorPo> scoreFactorPoList = new ArrayList<>(scoreFactorPoSet);
		// 计算全国排名和区域排名
		scoreFactorPoList.forEach(scoreFactorPo -> {
			int score = StringUtils.isEmpty(scoreFactorPo.getScore()) ? 0 : Integer.parseInt(scoreFactorPo.getScore());
			// 全国排名
			long countCountry = scoreFactorImportVoList
					.stream()
					// .filter(vo -> vo.getPeriod().equals(scoreFactorPo.getPeriod())
					.filter(vo -> period.equals(scoreFactorPo.getPeriod())
							&& vo.getScopeId().equals(scoreFactorPo.getScopeId())
							&& vo.getFactorName().equals(scoreFactorPo.getFactorCode())
							&& !vo.getStoreCode().equals(scoreFactorPo.getStoreCode())
							&& (StringUtils.isEmpty(vo.getScore()) ? 0 : Integer.parseInt(vo.getScore())) > score).count();
			scoreFactorPo.setRankCountry((int) countCountry + 1);
			// 区域排名
			Long scopeId = scoreFactorPo.getScopeId();
			String storeCode = scoreFactorPo.getStoreCode();
			String regionCode = ToolsUtils.getRegionCode(scopeId, storeCode, provincePoList, cityPoList, storePoList);
			long countScope = scoreFactorImportVoList
					.stream()
					// .filter(vo -> vo.getPeriod().equals(scoreFactorPo.getPeriod())
					.filter(vo -> vo.getScopeId().equals(scoreFactorPo.getScopeId())
							&& StringUtils.isNotBlank(vo.getRegionCode())
							&& vo.getRegionCode().equals(regionCode)
							&& vo.getFactorName().equals(scoreFactorPo.getFactorCode())
							&& !vo.getStoreCode().equals(scoreFactorPo.getStoreCode())
							&& (StringUtils.isEmpty(vo.getScore()) ? 0 : Integer.parseInt(vo.getScore())) > score).count();
			scoreFactorPo.setRankScope((int) countScope + 1);
		});

		scoreFactorService.deleteByPeriod(period);
		scoreFactorService.saveBatch(scoreFactorPoList);
	}

	@Override
	public void importScoresChannel(MultipartFile file, String period) throws IOException {
		List<ProvincePo> provincePoList = provinceService.list();
		List<CityPo> cityPoList = cityService.list();
		List<StorePo> storePoList = storeService.list();

		DefaultExcelListener<T> scoreChannelImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), scoreChannelImportListener, ScoreChannelImportVo.class, 1, 1);
		List<ScoreChannelImportVo> scoreChannelImportVoList = ConvertUtils.convert(scoreChannelImportListener.getRows(), ScoreChannelImportVo.class);
		Set<ScoreChannelPo> scoreChannelPoSet = new HashSet<>();
		scoreChannelImportVoList.forEach(scoreChannelImportVo -> {
			ScoreChannelPo scoreChannelPo = new ScoreChannelPo();
			// scoreChannelPo.setPeriod(scoreChannelImportVo.getPeriod());
			scoreChannelPo.setPeriod(period);
			Long scopeId = getScopeIdFromName(scoreChannelImportVo.getScopeName());
			scoreChannelImportVo.setScopeId(scopeId);
			if (null != scopeId) {
				String storeCode = scoreChannelImportVo.getStoreCode().trim();
				String regionCode = ToolsUtils.getRegionCode(scopeId, storeCode, provincePoList, cityPoList, storePoList);
				scoreChannelImportVo.setRegionCode(regionCode);
				scoreChannelPo.setScopeId(scopeId);
				scoreChannelPo.setStoreCode(storeCode);
				scoreChannelPo.setChannelCode(scoreChannelImportVo.getChannelName().trim());
				scoreChannelPo.setScore(scoreChannelImportVo.getScore().trim());
				scoreChannelPoSet.add(scoreChannelPo);
			}
		});
		List<ScoreChannelPo> scoreChannelPoList = new ArrayList<>(scoreChannelPoSet);
		// 计算全国排名和区域排名
		scoreChannelPoList.forEach(scoreChannelPo -> {
			int score = StringUtils.isEmpty(scoreChannelPo.getScore()) ? 0 : Integer.parseInt(scoreChannelPo.getScore());
			// 全国排名
			long countCountry = scoreChannelImportVoList
					.stream()
					// .filter(vo -> vo.getPeriod().equals(scoreChannelPo.getPeriod())
					.filter(vo -> period.equals(scoreChannelPo.getPeriod())
							&& vo.getScopeId().equals(scoreChannelPo.getScopeId())
							&& vo.getChannelName().equals(scoreChannelPo.getChannelCode())
							&& !vo.getStoreCode().equals(scoreChannelPo.getStoreCode())
							&& (StringUtils.isEmpty(vo.getScore()) ? 0 : Integer.parseInt(vo.getScore())) > score).count();
			scoreChannelPo.setRankCountry((int) countCountry + 1);
			// 区域排名
			Long scopeId = scoreChannelPo.getScopeId();
			String storeCode = scoreChannelPo.getStoreCode();
			String regionCode = ToolsUtils.getRegionCode(scopeId, storeCode, provincePoList, cityPoList, storePoList);
			long countScope = scoreChannelImportVoList
					.stream()
					// .filter(vo -> vo.getPeriod().equals(scoreChannelPo.getPeriod())
					.filter(vo -> period.equals(scoreChannelPo.getPeriod())
							&& vo.getScopeId().equals(scoreChannelPo.getScopeId())
							&& StringUtils.isNotBlank(vo.getRegionCode())
							&& vo.getRegionCode().equals(regionCode)
							&& vo.getChannelName().equals(scoreChannelPo.getChannelCode())
							&& !vo.getStoreCode().equals(scoreChannelPo.getStoreCode())
							&& (StringUtils.isEmpty(vo.getScore()) ? 0 : Integer.parseInt(vo.getScore())) > score).count();
			scoreChannelPo.setRankScope((int) countScope + 1);
		});
		scoreChannelService.deleteByPeriod(period);
		scoreChannelService.saveBatch(scoreChannelPoList);
	}

	@Override
	public void importScoresQuestion(MultipartFile file, String period) throws IOException {
		List<ChannelPo> channelPoList = channelService.list();

		DefaultExcelListener<T> scoreQuestionImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), scoreQuestionImportListener, ScoreQuestionImportVo.class, 2, 1);
		List<ScoreQuestionImportVo> scoreQuestionImportVoList = ConvertUtils.convert(scoreQuestionImportListener.getRows(), ScoreQuestionImportVo.class);
		Set<ScoreQuestionPo> scoreQuestionPoSet = new HashSet<>();
		scoreQuestionImportVoList.forEach(scoreQuestionImportVo -> {
			ScoreQuestionPo scoreQuestionPo = new ScoreQuestionPo();
			// scoreQuestionPo.setPeriod(scoreQuestionImportVo.getPeriod().trim());
			scoreQuestionPo.setPeriod(period);
			scoreQuestionPo.setStoreCode(scoreQuestionImportVo.getStoreCode().trim());
			channelPoList.stream().filter(channelPo -> channelPo.getQuestionPrefix().equals(scoreQuestionImportVo.getQuestionSeriesNo().trim().substring(0, 1))).findFirst().ifPresent(channelPo -> scoreQuestionPo.setChannelCode(channelPo.getCode()));
			String channelCode = scoreQuestionPo.getChannelCode();
			if (!StringUtils.isEmpty(channelCode)) {
				scoreQuestionPo.setQuestionSeriesNo(scoreQuestionImportVo.getQuestionSeriesNo().trim());
				if (null != scoreQuestionImportVo.getScore()) {
					scoreQuestionPo.setScore(scoreQuestionImportVo.getScore());
				}
				if (null != scoreQuestionImportVo.getGrade()) {
					scoreQuestionPo.setGrade(scoreQuestionImportVo.getGrade());
				}
				scoreQuestionPoSet.add(scoreQuestionPo);
			}

		});
		List<ScoreQuestionPo> scoreQuestionPoList = new ArrayList<>(scoreQuestionPoSet);
		scoreQuestionService.deleteByPeriod(period);
		scoreQuestionService.saveBatch(scoreQuestionPoList);

	}

	@Override
	public void importQuestionComplain(MultipartFile file, String period) throws IOException {

		DefaultExcelListener<T> questionComplainImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), questionComplainImportListener, QuestionComplainImportVo.class, 3, 1);
		List<QuestionComplainImportVo> questionComplainImportVoList = ConvertUtils.convert(questionComplainImportListener.getRows(), QuestionComplainImportVo.class);
		Set<QuestionComplainPo> questionComplainPoSet = new HashSet<>();
		questionComplainImportVoList.forEach(questionComplainImportVo -> {
			QuestionComplainPo questionComplainPo = new QuestionComplainPo();
			// questionComplainPo.setPeriod(questionComplainImportVo.getPeriod());
			questionComplainPo.setPeriod(period);
			questionComplainPo.setSeriesNo(questionComplainImportVo.getSeriesNo());
			questionComplainPo.setStoreCode(questionComplainImportVo.getStoreCode());
			questionComplainPo.setFactorCode(questionComplainImportVo.getFactorCode());
			questionComplainPo.setDescription(questionComplainImportVo.getDescription());
			questionComplainPoSet.add(questionComplainPo);
		});
		List<QuestionComplainPo> questionComplainPoList = new ArrayList<>(questionComplainPoSet);
		questionComplainService.deleteByPeriod(period);
		questionComplainService.saveBatch(questionComplainPoList);

	}

	@Override
	public void importRegulationScore(MultipartFile file, String period) throws IOException {

		DefaultExcelListener<T> regulationScoreImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), regulationScoreImportListener, RegulationScoreImportVo.class, 4, 1);
		List<RegulationScoreImportVo> regulationScoreImportVoList = ConvertUtils.convert(regulationScoreImportListener.getRows(), RegulationScoreImportVo.class);
		Set<RegulationScorePo> regulationScorePoSet = new HashSet<>();
		Set<RegulationScoreChannelPo> regulationScoreChannelPoSet = new HashSet<>();

		regulationScoreImportVoList.forEach(regulationScoreImportVo -> {

			if ("综合".equals(regulationScoreImportVo.getChannelName().trim())) {
				RegulationScorePo regulationScorePo = new RegulationScorePo();
				// regulationScorePo.setPeriod(regulationScoreImportVo.getPeriod());
				regulationScorePo.setPeriod(period);
				regulationScorePo.setStoreCode(regulationScoreImportVo.getStoreCode());
				regulationScorePo.setRegulationDescription(regulationScoreImportVo.getFactorName().trim() + ";" + regulationScoreImportVo.getElementName().trim() + ";" + regulationScoreImportVo.getRegulationDescription().replace("\n", "<br/>").trim());
				regulationScorePo.setScoreType(getScoreType(regulationScoreImportVo.getScoreType()));
				regulationScorePo.setScore(regulationScoreImportVo.getScore());
				regulationScorePo.setGrade(regulationScoreImportVo.getGrade());

				regulationScorePoSet.add(regulationScorePo);
			} else {
				RegulationScoreChannelPo regulationScoreChannelPo = new RegulationScoreChannelPo();
				// regulationScoreChannelPo.setPeriod(regulationScoreImportVo.getPeriod());
				regulationScoreChannelPo.setPeriod(period);
				regulationScoreChannelPo.setStoreCode(regulationScoreImportVo.getStoreCode().trim());
				regulationScoreChannelPo.setRegulationDescription(regulationScoreImportVo.getFactorName().trim() + ";" + regulationScoreImportVo.getElementName().trim() + ";" + regulationScoreImportVo.getRegulationDescription().replace("\n", "<br/>").trim());
				regulationScoreChannelPo.setChannelCode(regulationScoreImportVo.getChannelName().trim());
				regulationScoreChannelPo.setScoreType(getScoreType(regulationScoreImportVo.getScoreType()));
				regulationScoreChannelPo.setScore(regulationScoreImportVo.getScore());
				regulationScoreChannelPo.setGrade(regulationScoreImportVo.getGrade());

				regulationScoreChannelPoSet.add(regulationScoreChannelPo);
			}
		});
		List<RegulationScorePo> regulationScorePoList = new ArrayList<>(regulationScorePoSet);
		regulationScoreService.deleteByPeriod(period);
		regulationScoreService.saveBatch(regulationScorePoList);
		List<RegulationScoreChannelPo> regulationScoreChannelPoList = new ArrayList<>(regulationScoreChannelPoSet);
		regulationScoreChannelService.deleteByPeriod(period);
		regulationScoreChannelService.saveBatch(regulationScoreChannelPoList);
	}


	@Override
	public void resetPasswordBatch(MultipartFile file) throws IOException {
		UserPo userPo = new UserPo();
		userPo.setPassword(encoder.encode(CsrConstant.DEFAULT_RAW_PASSWORD));
		userPo.setChangeFlag(DictionaryType.CHANGE_PASSWORD_NEED);

		DefaultExcelListener<T> resetPasswordBatchImportListener = new DefaultExcelListener<>();
		EasyExcelUtils.asyncReadModel(file.getInputStream(), resetPasswordBatchImportListener, ResetPasswordBatchImportVo.class, 0, 1);
		List<ResetPasswordBatchImportVo> resetPasswordBatchImportVoList = ConvertUtils.convert(resetPasswordBatchImportListener.getRows(), ResetPasswordBatchImportVo.class);
		String usernames = resetPasswordBatchImportVoList.stream().map(ResetPasswordBatchImportVo::getUsername).collect(Collectors.joining(","));
		LambdaUpdateWrapper<UserPo> wrapper = Wrappers.lambdaUpdate();
		wrapper.in(UserPo::getUsername, usernames.split(","));
		userService.update(userPo, wrapper);
	}

	private Long getScopeIdFromName(String scopeName) {
		Long scopeId = null;
		switch (scopeName.trim()) {
			case "national":
				scopeId = DictionaryType.SCOPE_ID_NATIONAL;
				break;
			case "region":
				scopeId = DictionaryType.SCOPE_ID_REGION;
				break;
			case "province":
				scopeId = DictionaryType.SCOPE_ID_PROVINCE;
				break;
			case "city":
				scopeId = DictionaryType.SCOPE_ID_CITY;
				break;
			case "parent dealer":
				scopeId = DictionaryType.SCOPE_ID_SUPERIOR;
				break;
			case "dealer":
				scopeId = DictionaryType.SCOPE_ID_STORE;
				break;
			default:
				break;
		}
		return scopeId;
	}

	private Integer getScoreType(String strScoreType) {
		Integer scoreType = null;
		if ("考核".equals(strScoreType.trim())) {
			scoreType = DictionaryType.SCORE_TYPE_ID_EVALUATE;
		} else if ("加分".equals(strScoreType.trim())) {
			scoreType = DictionaryType.SCORE_TYPE_ID_BONUS;
		} else if ("扣分".equals(strScoreType.trim())) {
			scoreType = DictionaryType.SCORE_TYPE_ID_DEDUCT;
		}
		return scoreType;
	}

}
