/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//http://www.tutorialspoint.com/jqueryui/jqueryui_menu.htm
//basic using javascript http://www.w3schools.com/howto/howto_js_dropdown.asp
//http://www.w3schools.com/howto/howto_css_dropdown.asp
//https://api.jqueryui.com/theming/icons/


$(document).ready(
        function () {
            
            /* Menu code */
            $(document).click(function (event) {
                if (!$(event.target).closest('nav.topMenu').length &&
                        !$(event.target).is('nav.topMenu')) {
                    $('nav.topMenu  ul ul ul').removeClass("show");
                    $('nav.topMenu  ul ul').removeClass("show");
                }
            })


            $('nav.topMenu aside').each(function (idx, elem)
            {

                //icons
                if ($(elem).parents('ul').length === 1)
                {
                    // top menu
                    $(this).append('<span class="ui-icon ui-icon-circle-triangle-s"></span>');
                    $(elem).bind("click", function ()
                    {

                        // console.log($(this).text()+" whoopee!!");
                        $('nav aside').next().removeClass("show");
                        $(this).next().toggleClass("show");
                    });
                } else
                {
                    //submenu

                    $(this).append('<span class="ui-icon ui-icon-circle-triangle-e subMenu"></span>');
                    //$('nav.topMenu  ul ul ul') clear all sub sub menus
                    $(elem).bind("click", function ()
                    {

                        // console.log($(this).text()+" whoopee!!");
                        $('nav.topMenu  ul ul ul').removeClass("show");
                        $(this).next().toggleClass("show");
                    });

                }


            });
            /* menu code */
            
     
        jQuery.ui.tabs.prototype._isLocal = function() { return true; };
             $('.tabs').tabs();
        });
        
        function showExplainDialog()
        {
            $('#explainDialog').dialog("open");
        }
        
       