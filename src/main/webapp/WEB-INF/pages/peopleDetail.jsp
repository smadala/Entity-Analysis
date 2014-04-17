<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Freebase Suggest Example - Find topics of type /film/director (Movie Director)</title>

    <link href="http://www.gstatic.com/freebase/suggest/4_2/suggest.min.css" rel="stylesheet" type="text/css">
    <link href="css/prettify.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/prettify.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://www.gstatic.com/freebase/suggest/4_2/suggest.min.js"></script>

    <style>
        body {
            font-family: Arial;
        }

        h1 {
            font-size: 1.7em;
        }

        pre.prettyprint {
            padding: 16px;
            background: #eee;
            display: inline-block;
            margin: 0;
        }

        #myinput1, #myinput2 {
            width: 200px;
            padding: 4px 6px;
            font-size: 0.9em;
            margin: 0 0 20px;
        }

        #response1, #response2 {
            display: none;
        }
        #detail{
            display: inline-block;
            /*position: fixed;*/
            top: 0;
            bottom: 0;
            left: 100px;
            right: 100px;
            margin: auto;
            background-color: #f3f3f3;
        }
        #body{
            align-items: center;
        }
    </style>
</head>
<body >
<div id="body" >
    <table style="width:100%;">
        <tr>
            <td style="width:50%;vertical-align:top">
                <p><strong>People:</strong></p>
                <input id="myinput1" type="text">
                <script type="text/javascript">
                    $(function () {
                        $("#myinput1").suggest({filter: '(all type:/people/person)'}).bind("fb-select", function (e, data) {
                            $("#response1 pre").text(JSON.stringify(data.mid, null, '  '));
                            var tokens=document.URL.split("=");
                            window.location.assign( tokens[0] +"="+data.mid);
                        })
                    });
                </script>

            </td>
        </tr>
    </table>
</div>
<div id="detail" >

    <c:forEach var="image" items="${personData.person.images}" >     
        <img src=${image}>
    </c:forEach><br>

    <strong>Name:</strong>  ${personData.person.name}<br>
    <strong>Born: </strong> ${personData.person.dateOfBirth}, ${personData.person.placeOfBirth}<br>
    <strong>Profession: </strong> ${personData.person.profession}<br>
    <strong>About: </strong> ${personData.person.desc.desc}
    <a href=${personData.person.desc.providerURL}> ${personData.person.desc.provider} </a><br>



    <strong>Awards:</strong> ${personData.person.awards}<br>
    <%--${personData}--%>

    <div id="twitter">
        <h3><strong>Twitter</strong></h3>
            <img src=${personData.twitterData.user.profileImageURL}><br>
            <strong>Name: </strong><a href=${personData.twitterData.screenName}> ${personData.twitterData.user.name} </a>
        <c:if test="${personData.twitterData.user.verified}">
            <img src="http://www.socialmedialife.gr/wp-content/uploads/2013/06/twitter-verified-icon.png">
        </c:if><br>
            <strong>Tweets:</strong>${personData.twitterData.user.statusesCount}<br>
            <strong>Following: </strong>${personData.twitterData.user.friendsCount}<br>
            <strong>Followers: </strong>${personData.twitterData.user.followersCount}<br>
            <strong>Description: </strong>${personData.twitterData.user.description}<br>
            <strong>Recent tweet:</strong>${personData.twitterData.user.status.text}  ${personData.twitterData.user.status.createdAt}<br>
    </div>

    <div id="facebook">
        <h3><strong>Facebook</strong></h3>
        <img src=${personData.facebookData.page.picture}><br>
        <strong>Name: </strong><a href=${personData.facebookData.page.link}> ${personData.facebookData.page.name} </a>
        <strong>Likes:</strong>${personData.facebookData.page.likes}<br>
        <strong>Talking about this: </strong>${personData.facebookData.page.talkingAboutCount}<br>
        <%--<strong>Followers: </strong>${personData.twitterData.user.followersCount}<br>
        <strong>Description: </strong>${personData.twitterData.user.description}<br>
        <strong>Recent tweet:</strong>${personData.twitterData.user.status.text}  ${personData.twitterData.user.status.createdAt}<br>--%>
    </div>


    ${personData}
</div>
</body>
</html>