function openBurger() {
    var burg = document.getElementById("teacherMenu");
    if (burg.className === "navBarAdmin") {
        burg.className += " openTeacherMenu";
    } else {
        burg.className = "navBarAdmin"
    }
}

function openUserProfile() {
    var userProfile = document.getElementById("userProfileBox");
    if (userProfile.className === "userProfileBox col-lg-12 col-md-12") {
        userProfile.className += " openProfileBox"
    } else {
        userProfile.className = "userProfileBox col-lg-12 col-md-12"
    }
}

function openAdminProfile() {
    var adminProfile = document.getElementById("adminProfileBox");
    if (adminProfile.className === "adminProfileBox") {
        adminProfile.className += " openProfileBox"
    } else {
        adminProfile.className = "adminProfileBox"
    }
}

function openCreateInformation() {
    var add = document.getElementById("infoBox");
    if (add.className === "infoBox") {
        add.className += " openCreateInfo"
    } else {
        add.className = "infoBox"
    }
}

function openCreateClassroom() {
    var add = document.getElementById("adminOnly");
    if (add.className === "adminOnly") {
        add.className += " openCreateClass"
    } else {
        add.className = "adminOnly"
    }
}

function openQuestionContent() {
    var hiddenAns = document.getElementById("hiddenAnswer");
    if (hiddenAns.className === "hiddenAnswer") {
        hiddenAns.className += " showAnswer"
    } else {
        hiddenAns.className = "hiddenAnswer"
    }
}

// function showStudentFrame() {
//     var x = document.getElementById("classroomsLeftContent");
//     if (x.style.visibility === "hidden") {
//         x.style.visibility = "visible";
//     }
// }

//Teacher page - admin access utilities


// to update the question label
$(function () {
    // $(".updateQuestion").on("click", function (ev) {
    //     ev.stopPropagation();
    //     let a = $(this).parent().parent().parent().prev().find("p").attr("id");
    //     console.log("my attribute id" + a);
    //     let b = $(this).parent().parent().parent().prev().find("form").attr("id");
    //     console.log("my content attribute id" + b);
    //     var x = document.getElementById(a);
    //     var y = document.getElementById(b);
    //     if (x.style.display === "flex")
    //         console.log("questionLabel is in flex display");
    //     x.style.display = "none";
    //     console.log("changed to none display");
    //     y.style.display = "flex"
    // });
    // once the question label clicked,
    $(".questionLabel").on("click", function (ev) {
        ev.stopPropagation();

        //find the input element
        var myInputId = $(this).next().attr("id");
        var myInput = document.getElementById(myInputId);

        // hide the label and display the input form
        if (this.style.display == "flex") {
            console.log("recognized clicked question")
            this.style.display = "none";
            myInput.style.display = "flex";
        }
    })

    //excludes the form from the function that initiates once body is clicked
    $(".updateQForm").on("click", function (ev) {
        ev.stopPropagation();
    })
    
    //to reverse the update form
    $("body").on("click", function () {

        //getting the elements to be exchanged
        var getForms = document.getElementsByClassName("updateQForm")
        var getFrames = document.getElementsByClassName("questionLabel")

        //creating an array of all "updateQForm" elements
        var formDisplayed = Array.prototype.map.call(getForms, function (getForm) {
            return getForm.style.cssText === "display: flex;"
        })

        //a loop to find a "form" being open (true means present, false hidden)
        for (let i = 0; i < getForms.length; i++) {
            //if the form is open, hide it - change display to none and show the label
            if (formDisplayed[i] === true) {
                getForms[i].style.cssText = "display: none;";
                getFrames[i].style.cssText = "display: flex;"
            }
        }
    })
});

$(function () {
    // once the option label clicked,
    $(".optionContent").on("click", function (e) {
        e.stopPropagation();

        //find the input element
        var myInputId = $(this).next().attr("id");
        var myInput = document.getElementById(myInputId);

        // hide the option and display the input form
        if (this.style.display == "table-cell") {
            console.log("recognized clicked option id")
            this.style.display = "none";
            myInput.style.display = "table-cell";
        }
    })

    //excludes the input  field from the body function
    $(".newOptionContent").on("click", function (ev) {
        ev.stopPropagation();
    })
    //excludes the submit button from the body function
    $(".renameOptionBtn").on("click", function (ev) {
        ev.stopPropagation();
    })

    //to reverse the update form
    $("body").on("click", function () {

        var getForms = document.getElementsByClassName("updateOption")
        var getFrames = document.getElementsByClassName("optionContent")

        var formDisplayed = Array.prototype.map.call(getForms, function (getForm) {
            return getForm.style.cssText === "display: table-cell;"
        })

        for (let i = 0; i < getForms.length; i++) {
            if (formDisplayed[i] === true) {
                getForms[i].style.cssText = "display: none;";
                getFrames[i].style.cssText = "display: table-cell;"
            }
        }
    })
});


function removeToggle() {
    console.log("entered toggle remove function")
    var x = document.getElementById("updateInfoFrame");
    var y = document.getElementById("updateInfoForm");
    if (y.style.display === "flex") {
        console.log("entered the first statement")
        y.style.display = "none";
        x.style.display = "flex";
    } else {
        console.log("form is not displayed, cannot do anything")
        return;
    }
};

// function that toggles the input type - to text (show psw) and password (hide psw)
function togglePassword(input) {
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
}


$(document).ready(function () {
    // event listener click on i tag ....- .toggle-password is pointing to the specific class of the eye icon
    $(".toggle-password").click(function () {
        // store the retrieved class name of the clicked icon
        let clickedIcon = $(this).attr("id");
        // store the value of the toggle attribute of each clicked icon
        let inputPsw = $($(this).attr("toggle"));
        // check if the icon clicked is concerned
        if (clickedIcon === "eyes1") {
            // icon change
            $("#eyes1").toggleClass("fa-eye-slash", "fa-eye");
        } else if (clickedIcon === "eyes2") {
            // icon change
            $("#eyes2").toggleClass("fa-eye-slash", "fa-eye");
        } else if (clickedIcon === "eyes3") {
            // icon change
            $("#eyes3").toggleClass("fa-eye-slash", "fa-eye");
        }
        // call the function that toggles the type of selected input ID
        togglePassword(inputPsw);
    });
});

$(".profilePassword").click(function () {
    $(this).replaceWith('<input class = "col-6" value="Please click on Update" disabled>');
});

// listen to the click of the arrow
$(document).ready(function () {
    // arrow animation
    $(".answerToggle").click(function () {
        $(this).toggleClass("fa-sort-down fa-sort-up");
        // store id on click
        let questionId = "." + $(this).attr('id');
        console.log(questionId);
        $(questionId).toggle();

    });

    // locker animation
    $('.questionLock').click(function () {
        $(this).toggleClass("fa-lock fa-lock-open");
    });

    // retrieve text from question_content on click
    function questionContent() {
        // target the text on the div
        let text = document.getElementsByClassName("questionLabel").valueOf();
        alert(text);
        // display it
    }

});
