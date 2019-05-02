<!DOCTYPE HTML>
<html>

    <head>
        <title>${appTitle}</title>

        <link href="webjars/jsapp/foundation-icons.css"   type="text/css" rel="stylesheet"  />  
        <link href="webjars/jsapp/jquery-ui-theme.css" rel="stylesheet" type="text/css"/>  
        <link href="css/app_code.css" rel="stylesheet" type="text/css"/>
        
        <script src="/webjars/jquery/2.2.1/dist/jquery.min.js"  type="text/javascript"></script>  
        <script src="/webjars/jquery-ui/1.11.4/jquery-ui.min.js"  type="text/javascript"></script>  
        <script src="/js/ui-init.js" type="text/javascript"></script>   
        <link href="webjars/jsapp/main_app_style.css" rel="stylesheet" type="text/css"/>
        </head>
    <body>

        <section id="pageContainer">
            <header>
                <figure class="logo">${appTitle}</figure>
                <nav class="topMenu grouping">


                    <ul>
                        <li><a target="_blank" href="/swagger-ui.html">Swagger Docs</a></li> 
                        <li><a target="_blank" href="/actuator/mappings">Actuator Mappings</a></li> 
                        <li><aside>GraphQL</aside>
                            <ul>
                                <li><a  href="/graphQLQueries">GraphQL Queries</a></li> 
                                <li><a  href="/demoForGraphQL">GraphQL Demo</a></li> 
                            </ul>
                        </li>
                        
                        <li><a  href="/demos">Demos</a></li> 
                        <li><a  href="/data">Schemas</a></li> 
                        <li><a href="/">Home</a></li>
                        </ul>    


                    </nav>
                </header>
            <section id="main" class="grouping">
                <div class="mainPageContainer">
                    <!--begin content-->

