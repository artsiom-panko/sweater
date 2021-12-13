<#import "parts/login.ftlh" as login>
<#import "parts/common.ftlh" as common>

<@common.page>
    Add new user
    ${message!}
    <@login.login "/registration"/>
</@common.page>