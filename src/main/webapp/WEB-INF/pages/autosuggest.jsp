<html>
<head>
    <title>Entity Analysis</title>

    <link href="http://www.gstatic.com/freebase/suggest/4_2/suggest.min.css" rel="stylesheet" type="text/css">
    <link href="css/prettify.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/prettify.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://www.gstatic.com/freebase/suggest/4_2/suggest.min.js"></script>


</head>
<body >
<div id="body">
    <div class="wrapper col1">
        <div id="header">
            <div id="logo">
                <h1><a href="#">ENTITY ANALYSIS</a></h1>
                <p><strong>IRE PROJECT</strong></p>
            </div>

            <div id="newsletter">
                <img src="logo_name.gif" alt="IIIT" />
            </div>
            <br class="clear" />
        </div>
    </div>
    <table style="width:100%">
        <tr>
            <td style="width:50%;vertical-align:top">
                <p><strong>People:</strong></p>
                <input id="myinput1" type="text">
                <script type="text/javascript">
                    $(function () {
                        $("#myinput1").suggest({filter: '(all type:/people/person)'}).bind("fb-select", function (e, data) {
                            $("#response1 pre").text(JSON.stringify(data.mid, null, '  '));
                            window.location.assign( document.URL +"people?id="+data.mid);
                        })
                    });
                </script>

            </td>
        </tr>
    </table>
</div>
</div>

</body>
</html>