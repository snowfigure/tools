package com.sf.kits.yui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class YuiUtil {
	/**
	 * Insert a line break after the specified column number
	 * 多少字符换一行
	 */
	static int linebreakpos = -1;
	/**
	 * obfuscate（JS）
	 * 混淆
	 */
    static boolean munge=true;  
    /**
     * Display informational messages and warnings（JS）
     * 显示消息和警告
     */
    static boolean verbose=false;  
    /**
     *  Preserve all semicolons（JS）
     *  保存所有分号
     */
    static boolean preserveAllSemiColons=false;  
    /**
     * Disable all micro optimizations（JS）
     * 禁用所有的微观优化
     */
    static boolean disableOptimizations=false;
    
    
    /**
     * JS压缩
     * @param source	压缩前的字符串（尽量使用UTF-8的编码，使用POST的方式提交数据）
     * @return 压缩后的数据
     */
	public static String JsZip(String source,Boolean _munge){
		try {
			Reader in = new InputStreamReader(
						new ByteArrayInputStream(source.getBytes("UTF-8")),
						"UTF-8");
			StringWriter writer = new StringWriter();
			JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {
				
				public void warning(String message, String sourceName,  
                        int line, String lineSource, int lineOffset) {
					System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
				}
				
				public EvaluatorException runtimeError(String message, String sourceName,  
                        int line, String lineSource, int lineOffset) {
					System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
					 return new EvaluatorException(message); 
				}
				
				public void error(String message, String sourceName,  
                        int line, String lineSource, int lineOffset) {
					System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
				}
			});
			
			compressor.compress(writer, linebreakpos, _munge, verbose, preserveAllSemiColons, disableOptimizations);
			
			String result = writer.toString();
			writer.close();
			in.close();
			
			return result;
			
		} catch (UnsupportedEncodingException e) {
			System.err.println("无法解析的编码格式 ：UnsupportedEncodingException ");
			e.printStackTrace();
		} catch (EvaluatorException e) {
			System.err.println("EvaluatorException");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO数据流错误：IOException");
			e.printStackTrace();
		}
		return "出现未知的错误";
	}
	
	
	
	
	
	/**
     * Css压缩
     * @param source	压缩前的字符串（尽量使用UTF-8的编码，使用POST的方式提交数据）
     * @return 压缩后的数据
     */
	public static String CssZip(String source){
		try {
			Reader in = new InputStreamReader(
						new ByteArrayInputStream(source.getBytes("UTF-8")),
						"UTF-8");
			StringWriter writer = new StringWriter();
			CssCompressor compressor = new CssCompressor(in);
			compressor.compress(writer, linebreakpos);
			String result = writer.toString();
			writer.close();
			in.close();
			
			return result;
			
		} catch (UnsupportedEncodingException e) {
			System.err.println("无法解析的编码格式 ：UnsupportedEncodingException ");
			e.printStackTrace();
		} catch (EvaluatorException e) {
			System.err.println("EvaluatorException");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO数据流错误：IOException");
			e.printStackTrace();
		}
		return "出现未知的错误";
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String str = 	
		"function getUrlParam(name) {\n"+
		"	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)'); // 构造一个含有目标参数的正则表达式对象\n"+
		"	var r = window.location.search.substr(1).match(reg); // 匹配目标参数\n"+
		"	if (r != null)\n"+
		"		return unescape(r[2]);\n"+
		"	return null; //返回参数值\n"+
		"}\n";

		String result = YuiUtil.JsZip(str,true);
		System.out.println(result);
	}
	
}
