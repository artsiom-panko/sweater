<#import "parts/login.ftlh" as login>
<#import  "parts/common.ftlh" as common>

<@common.page>
    Login page
    <@login.login "/login"/>
    <a href="/registration">Registration</a>
</@common.page>