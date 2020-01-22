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
        if(userProfile.className === "profileBox col-lg-5 col-md-4"){
            userProfile.className += " openProfileBox"
        }
        else{
            userProfile.className = "profileBox col-lg-5 col-md-4"
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