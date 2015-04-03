<#include "/WEB-INF/nav/_nav_layout.ftl"/>
<@layout info>
<div class="content">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="links">
        <#include "/WEB-INF/nav/0_comm.ftl"/>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="links">
        <#include "/WEB-INF/nav/1_tech.ftl"/>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="links">
        <#include "/WEB-INF/nav/2_webui.ftl"/>
    </div>
</div>

</@layout>
<script>
    $(function(){
        $('li').bind(
           "click",function(){
                $('li').each(function(){
                    $(this).removeClass('active');
                });
                $(this).addClass('active');
            }
        );
    });

</script>
