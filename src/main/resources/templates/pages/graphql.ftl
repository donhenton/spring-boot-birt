<#include "../common/header.ftl">
 
<!-- the url for graphql is defined in dev.properties via HomeController -->
<script>
window.graphqlUrl = "${graphqlUrl}";

</script>



 <h3>React Demo</h3>
 <div class="row well">Demonstration of a graphQL query and a React front end</div>

<div id="graphqlDemo"></div>
 
<script src="/js/react/bundle.js" type="text/javascript"></script>
<#include "../common/footer.ftl">
