<#include "/WEB-INF/comm/_layout.ftl"/>
<@layout info>

<div class="col-xs-12 col-sm-8 col-md-8">
    <div class="panel panel-default">
        <div class="panel-heading">
            工具分类索引
        </div>
        <div class="panel-body">
            <table class="table table-bordered table-hover">
                <tr>
                    <th width="120px" rowspan=2>对照表</th>
                    <td><a href="/contrast/hct">HTTP Content-type</a></td>
                    <td><a href="/contrast/hec">HTML转义字符</a></td>
                    <td><a href="/contrast/rgb">RGB颜色参考</a></td>
                    <td><a href="/contrast/ascii">ASCII对照表</a></td>
                </tr>
                <tr>
                    <td><a href="/contrast/tup">TCP/UDP端口</a></td>
                    <td><a href="/contrast/hsc">Http状态码详解</a></td>
                    <td><a href="/apidocs/">API文档</a></td>
                    <td></td>
                </tr>
                <tr>
                    <th>代码处理</th>
                    <td><a href="/format/css">CSS压缩</a></td>
                    <td><a href="/format/js">JS压缩</a></td>
                    <td><a href="/format/xml">XML格式化/格式化</a></td>
                    <td></td>
                </tr>
                <tr>
                    <th>加密转码</th>
                    <td><a href="/encrypt/ecp">通用化加密</a></td>
                    <td><a href="/encrypt/barcode">条码生成</a></td>
                    <td><a href="/encrypt/morse">摩斯密码翻译</a></td>
                    <td></td>
                </tr>
                <tr>
                    <th rowspan=2>便民生活</a></th>
                    <td><a href="/life/bank">银行卡信息查询</a></td>
                    <td><a href="/life/weather">天气查询</a></td>
                    <td><a href="/life/kuaidi">快递查询</a></td>
                    <td></td>
                </tr>
                <tr>
                    <td><a href="/life/wxeditor">微信编辑器</a></td>
                    <td><a href="/life/qrcode">二维码生成器</a></td>
                    <td><a href="/life/meitups">美图秀秀</a></td>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="hidden-xs hidden-md col-md-4">
    <iframe width="100%" height="515" class="share_self"
            frameborder="0" scrolling="no"
            src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=515&fansRow=2&ptype=1&speed=0&skin=9&isTitle=1&noborder=1&isWeibo=1&isFans=1&uid=2202012815&verifier=a0641c19&dpc=1">
    </iframe>
</div>
</@layout>