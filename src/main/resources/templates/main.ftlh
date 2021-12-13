<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>

<@common.page>
    <div>
        <@login.logout/>
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Message"/>
            <input type="text" name="tag" placeholder="Tag"/>
            <input type="file" name="file">
            <button type="submit">Add</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div>
        <div>Message List</div>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter!}">
            <button type="submit">Filter</button>
        </form>
        <#list messages as message>
            <div>
                <b>${message.id}</b>
                <span>${message.text}</span>
                <i>${message.tag}</i>
                <strong>${message.author.username}</strong>
                <div>
                    <#if message.filename??>
                        <img src="/img/${message.filename}">
                    </#if>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@common.page>