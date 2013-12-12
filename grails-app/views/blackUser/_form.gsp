<%@ page import="com.emag.gamecms.domain.system.BlackUser" %>



<div class="fieldcontain ${hasErrors(bean: blackUserInstance, field: 'msisdn', 'error')} required">
	<label for="msisdn">
		<g:message code="blackUser.msisdn.label" default="Msisdn" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="msisdn" maxlength="20" required="" value="${blackUserInstance?.msisdn}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: blackUserInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="blackUser.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${blackUserInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: blackUserInstance, field: 'flag', 'error')} required">
	<label for="flag">
		<g:message code="blackUser.flag.label" default="Flag" />
		<span class="required-indicator">*</span>
	</label>
	<g:select  name="flag" from="${[[k:'1',v:'挖宝达人']]}" optionKey="k" optionValue="v" />
</div>

%{--<div class="fieldcontain ${hasErrors(bean: blackUserInstance, field: 'createTime', 'error')} required">
	<label for="createTime">
		<g:message code="blackUser.createTime.label" default="Create Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createTime" precision="minute"  value="${blackUserInstance?.createTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: blackUserInstance, field: 'updateTime', 'error')} ">
	<label for="updateTime">
		<g:message code="blackUser.updateTime.label" default="Update Time" />
		
	</label>
	<g:datePicker name="updateTime" precision="minute"  value="${blackUserInstance?.updateTime}" default="none" noSelection="['': '']" />
</div>--}%

