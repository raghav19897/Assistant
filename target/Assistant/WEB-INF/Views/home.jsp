<!DOCTYPE html>
<head>
    <title>Hello ${name}</title>
</head>

<script src="${pageContext.request.contextPath}/resources/scripts/scriptHome.js" type="text/javascript"></script>

<body onload="setInterval(setReminder,1); setCanvas(); openLinks()">

<canvas id="myCanvas" onclick="setCircles(event)"></canvas>

<script type="text/javascript">
    function openLinks(){
        if('${number}'>5){
            window.open("${link0}", "_blank");
            window.open("${link1}", "_blank");
            window.open("${link2}", "_blank");
            window.open("${link3}", "_blank");
            window.open("${link4}", "_blank");
            window.open("${link5}", "_blank");
        }
        else if('${number}'>4){
            window.open("${link0}", "_blank");
            window.open("${link1}", "_blank");
            window.open("${link2}", "_blank");
            window.open("${link3}", "_blank");
            window.open("${link4}", "_blank");
        }
        else if('${number}'>3){
            window.open("${link0}", "_blank");
            window.open("${link1}", "_blank");
            window.open("${link2}", "_blank");
            window.open("${link3}", "_blank");
        }
        else if('${number}'>2){
            window.open("${link0}", "_blank");
            window.open("${link1}", "_blank");
            window.open("${link2}", "_blank");
        }
        else if('${number}'>1){
            window.open("${link0}", "_blank");
            window.open("${link1}", "_blank");
        }
        else if('${number}'>0){
            window.open("${link0}", "_blank");
        }
    }

    function setReminder() {
        var fileName = '${user}';
        fetch('/resources/'+fileName+'.json')
            .then(function (response) {
                return response.json();
            }).then(function (data) {
            if (data.reminders !== 'No reminders'){
                document.getElementById('reminder').style.visibility = 'visible';
                document.getElementById('reminder').innerHTML = data.reminders;
            }
            else {
                document.getElementById('reminder').style.visibility = 'hidden';
            }
        })
            .catch(function (reason) {
                document.getElementById('reminder').style.visibility = 'hidden';
            });
    }
</script>

<link href="${pageContext.request.contextPath}/resources/stylesheets/theme.css" rel="stylesheet">

<form method="post" action="/command">
    <table>
        <tr>
            <td colspan="2"><input id="command_box" name="command" type="text" placeholder=" Enter command"/></td>
        </tr>
        <tr>
            <td><input id="search_but" type="submit" value="Search"></td>
            <td><input id="play_but" type="submit" value="   " onclick="document.getElementById('command_box').value += ' *';"/></td>
        </tr>
    </table>
</form>

<p id="notes_label">Notes</p>

<form method="post" action="/saveNotes">
    <table>
        <tr>
            <td><textarea id="notes_box" name="notes" placeholder="Take notes here">${notes}</textarea></td>
        </tr>
        <tr>
            <td><input id="notes_save_but" type="submit" value="    "/></td>
        </tr>
    </table>
</form>

<p id="reminder_label">Reminder</p>

<img id="add_reminder" src="${pageContext.request.contextPath}/resources/icons/addReminder.png" onclick="showHideReminders()">

<form method="post" action="/reminderSet">
    <table>
        <tr>
            <td colspan="2"><input id="reminder_box" name="reminder" type="text" placeholder="Enter reminder" /></td>
        </tr>
        <tr>
            <td><input id="date_pick" name="date" type="date" value="1999-01-01"/></td>
            <td><input id="time_set" name="time" type="time" value="00:00" /></td>
        </tr>
        <tr>
            <td><input id="reminder_but" type="submit" value="Set"></td>
        </tr>
    </table>
</form>

<p id="reminder" style="visibility: hidden" onclick="document.getElementById(this).style.visibility = 'hidden'"></p>
<a href="/logout"><button id="logout_but">Logout</button></a>

</body>
</html>
