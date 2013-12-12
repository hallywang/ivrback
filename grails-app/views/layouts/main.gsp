<html>
<head>
  <title><g:layoutTitle default="Grails"/></title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
  <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.treeview.css')}"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.ui.dialog.css')}"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.ui.datepicker.css')}"/>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.ui.all.css')}"/>
 
  <g:layoutHead/>
  <g:javascript library="jquery"/>
  %{-- <script type="text/javascript" src="/gamecms/js/jquery/jquery.ui.core.js"></script>--}%
  <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery/jquery-ui-1.8.2.custom.min.js')}"></script>

</head>
<body>
<g:layoutBody/>
</body>
</html>