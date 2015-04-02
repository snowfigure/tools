package com.snowfigure.tool.service;

import com.snowfigure.kits.idcard.Idcard;
import com.snowfigure.kits.idcard.IdcardUtils;
import com.snowfigure.kits.num.NumberUtil;
import com.snowfigure.model.tool.IdcardAreaCode;

public class Life_idcard_Service {
	public static Idcard validate(int type,String no){
		Idcard idcard = new Idcard(no);
		switch (type) {
		case 0:
			idcard = null;
			break;
		case 1:
			idcard = getMlcardnoInfo(idcard);
			break;
		case 2:
			
			break;
		case 3:
			idcard = upMlcardnoTo18(idcard);
			break;
		case 4:
			idcard = generate();
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		default:
			idcard = null;
			break;
		}
		return idcard;
	}
	/**
	 * 随机生成idcard信息
	 * @return
	 */
	private static Idcard generate(){
		String idcardno = generateIdCard();
		Idcard idcard = new Idcard(idcardno);
		idcard = getMlcardnoInfo(idcard);
		return idcard;
	}
	private static String generateIdCard(){
    	int _code = NumberUtil.random(0, 3652);
    	int code = IdcardAreaCode.me.findById(_code).getInt("code");
    	String birthdate = NumberUtil.getRadomDate();
    	int sequ = NumberUtil.random(100, 999);
    	String _idcardno = code + birthdate + sequ;
    	if (IdcardUtils.isNum(_idcardno)) {
            char[] cArr = _idcardno.toCharArray();
            if (cArr != null) {
                int[] iCard = IdcardUtils.converCharToInt(cArr);
                int iSum17 = IdcardUtils.getPowerSum(iCard);
                // 获取校验位
                String val = IdcardUtils.getCheckCode18(iSum17);
                _idcardno = _idcardno + val;
            }
        }
		return _idcardno;
    }
	/**
	 * 把15位身份证号码升级为18位身份证号码
	 * @param idcard
	 * @return
	 */
	private static Idcard upMlcardnoTo18(Idcard idcard){
		boolean flag = IdcardUtils.validateIdCard15(idcard.getIdcardno());
		if(!flag) return idcard;
		idcard.setIdcardno_15(idcard.getIdcardno());
		String _18 = IdcardUtils.conver15CardTo18(idcard.getIdcardno_15());
		idcard.setIdcardno(_18);
		idcard = getMlcardnoInfo(idcard);
		return idcard;
	}
	/**
	 * 获取大陆身份证号码的基本信息
	 * @param idcard	15位或者18位的身份证编号
	 * @return
	 */
	private static Idcard getMlcardnoInfo(Idcard idcard){
		boolean flag = IdcardUtils.validateCard(idcard.getIdcardno());
		if(!flag) return idcard;
		String address = getAddressByIdCard(idcard.getIdcardno());
		if("".equals(address)) return idcard;
		idcard.setValidate(flag);
		idcard.setAddress(address);
		idcard.setBirthdate(IdcardUtils.getBirthByIdCard(idcard.getIdcardno()));
		idcard.setGender(IdcardUtils.getGenderByIdCard(idcard.getIdcardno()));
		return idcard;
	}
	private static String getAddressByIdCard(String idCard){
    	int len = idCard.length();
    	String sAddressNum = "";
    	if (len == 15 || len == 18) {
            sAddressNum = idCard.substring(0, 6);
        }
    	return IdcardAreaCode.me.getAddressBycode(sAddressNum);
    }
}
