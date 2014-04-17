
<%--
  Created by IntelliJ IDEA.
  User: satya
  Date: 4/16/14
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>${personData.person.name} Entity Analysis</title>

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
            display: inline;
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

            height: 50px;
            bottom: 0px;
            left: 0px;
            right: 0px;
            margin-bottom: 0px;
            background-image: url('http://doc.akka.io/docs/akka/1.3.1/_static/dark-blue-bg-main.jpg');
        }
        #social_icons{
            float:right;
            margin-right: 50px;
        }
        strong{
            color:#1D51DD
        }
        #date{
            color:#B2B2B2;
        }
        #people{
            margin-bottom:30px;
        }
        #images{
            float:right;
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

            IRE PROJECT
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
        <input id="myinput1" type="text" placeholder="Search for another person">
        <script type="text/javascript">
            $(function () {
                $("#myinput1").suggest({filter: '(all type:/people/person)'}).bind("fb-select", function (e, data) {
                    $("#response1 pre").text(JSON.stringify(data.mid, null, '  '));
                    var tokens=document.URL.split("=");
                    window.location.assign( tokens[0] +"="+data.mid);
                })
            });
        </script>
    </div>
    <!--people-->
    <div id="detail" >

        <%--<c:forEach var="image" items="${personData.person.images}" >     
            <img src=${image}>
        </c:forEach><br>--%>
        <div id="images">
            ${personData.person.images}
            <img src=${personData.twitterData.user.profileImageURL}>
         </div>
         <div id="social_icons">
            <a href=${personData.person.desc.providerURL}><img src="https://cdn1.iconfinder.com/data/icons/socialmediaicons_v120/32/wikipedia.png" alt="wiki, wikipedia icon" data-action="select-size"></a>
            <a href=${personData.twitterData.screenName}> <img src="https://cdn1.iconfinder.com/data/icons/yooicons_set01_socialbookmarks/32/social_twitter_box_white.png"></a>
            <a href=${personData.facebookData.page.link} ><img src="https://cdn1.iconfinder.com/data/icons/yooicons_set01_socialbookmarks/32/social_facebook_box_white.png" alt="facebook, fb, network, social, white icon"></a>
         </div>


        <strong>Name:</strong>  ${personData.person.name}<br>
        <strong>Born: </strong> ${personData.person.dateOfBirth}, ${personData.person.placeOfBirth}<br>
        <strong>Profession: </strong> ${personData.person.profession}<br>
        <strong>About: </strong> ${personData.person.desc.desc}
        <%--<a href=${personData.person.desc.providerURL}> ${personData.person.desc.provider} </a>--%><br>



        <strong>Awards:</strong> ${personData.person.awards}<br>
        <%--${personData}--%>

        <div id="twitter">
            <%--<h3><strong>Twitter</strong></h3>--%>
            <%--<img src=${personData.twitterData.user.profileImageURL}><br>--%>
            <%--<strong>Name: </strong><a href=${personData.twitterData.screenName}> ${personData.twitterData.user.name} </a>
            <c:if test="${personData.twitterData.user.verified}">
                <img src="http://www.socialmedialife.gr/wp-content/uploads/2013/06/twitter-verified-icon.png">
            </c:if><br>
            <strong>Tweets:</strong>${personData.twitterData.user.statusesCount}<br>--%>
            <strong>Following: </strong>${personData.twitterData.user.friendsCount}<br>
            <strong>Followers: </strong>${personData.twitterData.user.followersCount}<br>
            <%--<strong>Description: </strong>${personData.twitterData.user.description}<br>--%>

        </div>

        <div id="facebook">
            <%--<h3><strong>Facebook</strong></h3>--%>
            <%--<img src=${personData.facebookData.page.picture}><br>--%>
            <%--<strong>Name: </strong><a href=${personData.facebookData.page.link}> ${personData.facebookData.page.name} </a>--%>
            <strong>Likes:</strong>${personData.facebookData.page.likes}<br>
            <strong>Talking about this: </strong>${personData.facebookData.page.talkingAboutCount}<br>
                <strong>Recent tweet:</strong>${personData.twitterData.user.status.text}  <span id="date" > ${personData.twitterData.user.status.createdAt}</span><br>
            <%--<strong>Followers: </strong>${personData.twitterData.user.followersCount}<br>
            <strong>Description: </strong>${personData.twitterData.user.description}<br>
            <strong>Recent tweet:</strong>${personData.twitterData.user.status.text}  ${personData.twitterData.user.status.createdAt}<br>--%>
        </div>


        <%--${personData}--%>
    </div>
    <!--detail-->
</div>
<!--main-->
<div id="footer">

</div>
<!-- footer-->
</body>
</html>