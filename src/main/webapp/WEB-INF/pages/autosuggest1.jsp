<%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/16/14
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Entity Analysis</title>

    <link href="http://www.gstatic.com/freebase/suggest/4_2/suggest.min.css" rel="stylesheet" type="text/css">
    <link href="css/prettify.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/prettify.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://www.gstatic.com/freebase/suggest/4_2/suggest.min.js"></script>

    <style>
        body {
            background-color: #EAF7F8;
            margin: 0;
        }

        a {
            outline: none;
            text-decoration: none;
        }

        #header a {
            color: #FFFFFF;

        }

        #header #logo h1 {
            font-size: 36px;

        }

        #header {
            padding: 30px 0;
            font-family: Georgia, "Times New Roman", Times, serif;

        }

        #logo #header {
            display: block;
            float: left;
            width: 300px;
            margin-top: 7px;
        }

        #header, #topbar, #intro, #services, #breadcrumb, #container, #copyright {
            position: relative;
            margin: 0 auto 0;
            display: block;
            width: 960px;
        }

        div.wrapper {
            display: block;
            width: 100%;
            margin: 0;
            text-align: left;
        }

        .col1 {
            color: #FFFFFF;
            background-image: url('http://doc.akka.io/docs/akka/1.3.1/_static/dark-blue-bg-main.jpg');
        }

        #header #newsletter {
            float: right;
            padding: 1px 1px 1px 1px;
            color: #000000;
        }

        img {
            border: 0;
            display: block;
            padding: 0;
            margin: 0;
        }

        #main {

            margin: 30px 200px;
        }

        input[type="text"] {
            width: 327px;
            height: 30px;
            font-family: "Times New Roman", Times, serif;
            font-style: normal;
            font-size: 23px;
        }

        #myinput1 {
            margin-left: 200px;
        }

        #footer {
            position: fixed;
            height: 50px;
            bottom: 0px;
            left: 0px;
            right: 0px;
            margin-bottom: 0px;
            background-image: url('http://doc.akka.io/docs/akka/1.3.1/_static/dark-blue-bg-main.jpg');
        }
    </style>

</head>
<body>
<div class="wrapper col1">
    <div id="header">
        <div id="logo">
            <h1>
                <a href='#'>Entity Analysis </a>
            </h1>

            <p><strong>IRE PROJECT</strong></p>
        </div>
        <!-- logo-->

    </div>
    <!-- header -->
</div>
<!-- wrapper-->

</div>
<div id="main">

    <div id="people">
        <h1> Poeple Search</h1>
        <input id="myinput1" type="text" placeholder="Search for a person">
        <script type="text/javascript">
            $(function () {
                $("#myinput1").suggest({filter: '(all type:/people/person)'}).bind("fb-select", function (e, data) {
                    $("#response1 pre").text(JSON.stringify(data.mid, null, '  '));
                    window.location.assign(document.URL + "people?id=" + data.mid);
                })
            });
        </script>
    </div>
    <!--people-->
    <div id="detail">
    </div>
    <!--detail-->
</div>
<!--main-->
<div id="footer">

</div>
<!-- footer-->
</body>
</html>