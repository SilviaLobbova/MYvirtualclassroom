function openBurger() {
    var burg = document.getElementById("teacherMenu");
    if(burg.className === "navBarAdmin"){
        burg.className += " openTeacherMenu";
    }
    else{
        burg.className = "navBarAdmin"
    }
}
function openUserProfile(){
        var userProfile = document.getElementById("userProfileBox");
        if(userProfile.className === "userProfileBox col-lg-12 col-md-12"){
            userProfile.className += " openProfileBox"
        }
        else{
            userProfile.className = "userProfileBox col-lg-12 col-md-12"
        }
    }
function openAdminProfile(){
    var adminProfile = document.getElementById("adminProfileBox");
    if(adminProfile.className === "adminProfileBox"){
        adminProfile.className += " openProfileBox"
    }
    else{
        adminProfile.className = "adminProfileBox"
    }
}
function openCreateClassroom(){
    var add = document.getElementById("adminOnly");
    if(add.className === "adminOnly"){
        add.className += " openCreateClass"
    }
    else{
        add.className = "adminOnly"
    }
}
function openQuestionContent(){
    var hiddenAns = document.getElementById("hiddenAnswer");
    if(hiddenAns.className === "hiddenAnswer"){
        hiddenAns.className += " showAnswer"
    }
    else{
        hiddenAns.className = "hiddenAnswer"
    }
}

// function openClassroomDetails(){
//     var hiddenDetails = document.getElementById("classesContent");
//
//     if (hiddenDetails.className === "classesContent"){
//         hiddenDetails.className += " showClassroom";
//         document.getElementById("classTitle").textContent = document.getElementById("classroomListItem").textContent;
//     }
//     else{
//         hiddenDetails.className= "classesContent"
//     }
// }

$(document).ready(function() {
    $(".studentFrame").hide();
    console.log("here I went")
    $(".classroomListItem").on('click', function() {
    // var value = $("#className li:selected").attr('text');
    // console.log(value);
    $(".studentFrame").show();
    console.log($(this).text())
    $('.classTitle').text($(this).text())
        $(".deleteFrameIcon").attr("value", $(this).text())
        $(".updateFrameIcon").attr("value", $(this).text())
        console.log($(".deleteFrameIcon").attr("value"))
        console.log($('.classTitle').attr("name"))
    // $('.classTitle li').val($(".classroomListItem").data('text'))
})
    $(".deleteFrame").on('click', function(){
        // $('this').attr("name", "ClassName")
        var className = $('.classTitle').text()
        if (window.confirm("Are you sure that you want to delete the classroom " + className + " ?")===true) {
            location.action = this.action;
        }else{
            window.location('/');
        }
    })
    // var elementClicked;
    // $("body").click(function(){
    //     elementClicked = true;
    // })
    var handler = function (){
        var className = $('.classTitle').text()
        console.log("entered function")
        $(".classTitle").replaceWith('<input class="classNameInput" name = "newValue" value= '+ className + ' >')
        $(".fa-pencil").replaceWith('<button class="renameBtn">OK</button>');
    }

    $(".fa-pencil").bind('click', handler);
    //     console.log("entered 2.function")
    //     var className = $('.classTitle').text()
    //     $(".classTitle").replaceWith('<input class="classNameInput" name = "newValue" value= '+ className + ' >')
    //     $(this).replaceWith('<button class="renameBtn">OK</button>')
    // })
    $( "body" ).on('click',function(evt) {
        console.log("entered 3;function")
        if(evt.target.className !== 'fa-pencil' && evt.target.id !== 'frameHeader') {
            console.log("OK")
            $(".fa-pencil").unbind('click', handler)
                console.log("NOK")
        }else{
            console.log("pas compris")
        }

    });
    // $('body').on('click', function(){
    //     $(".classNameInput").replaceWith($(".classTitle"))
    //     $(".renameBtn").replaceWith($(".fa-pencil"))
    // })

});

// function that toggles the input type - to text (show psw) and password (hide psw)
function togglePassword(input){
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    }
    else {
        input.attr("type", "password");
    }
}
// !!! jquery part do not delete it  !!!
$(document).ready(function() {
    // event listener click on i tag ....- .toggle-password is pointing to the specific class of the eye icon
    $(".toggle-password").click(function() {
        // store the retrieved class name of the clicked icon
        let clickedIcon = $(this).attr("id");
        // store the value of the toggle attribute of each clicked icon
        let inputPsw = $($(this).attr("toggle"));
        // check if the icon clicked is concerned
        if(clickedIcon === "eyes1") {
            // icon change
            $( "#eyes1" ).toggleClass( "fa-eye-slash", "fa-eye" );
        }
        else if(clickedIcon === "eyes2") {
            // icon change
            $( "#eyes2" ).toggleClass( "fa-eye-slash", "fa-eye" );
        }
        else if(clickedIcon === "eyes3") {
            // icon change
            $( "#eyes3" ).toggleClass( "fa-eye-slash", "fa-eye" );
        }
        // call the function that toggles the type of selected input ID
        togglePassword(inputPsw);
    });
});
$(".profilePassword").click(function() {
    $(this).replaceWith('<input class = "col-6" value="Please click on Update" disabled>');
});


