function openBurger() {
    var burg = document.getElementById("teacherMenu");
    if(burg.className === "navBarUser"){
        burg.className += " openTeacherMenu";
    }
    else{
        burg.className = "navBarUser"
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