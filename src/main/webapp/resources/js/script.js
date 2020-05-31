function checkConfirmPasswordMatch() {
    let password = $("#settings_password").val();
    let newPassword = $("#settings_new_password").val();
    let confirmPassword = $("#settings_password_repeat").val();
    let alertPanel = document.getElementById("password_alert");
    let submitBtn = document.getElementById('submit_btn')
    if (password.length === 0) {
        submitBtn.disabled = true;
    } else if (newPassword !== confirmPassword) {
        alertPanel.style.display = "block";
        submitBtn.disabled = true;
        $("#password_alert").html("Passwords don't match!");
    } else if (newPassword === confirmPassword && password.length === 0 ||
                newPassword.length === 0 && confirmPassword.length === 0 && password.length > 0) {
        alertPanel.style.display = "none";
        submitBtn.disabled = true;
    } else {
        alertPanel.style.display = "none";
        submitBtn.disabled = false;
    }
}

function checkNewPasswordCorrectness() {
    let password = $("#settings_password").val();
    let newPassword = $("#settings_new_password").val();
    let confirmPassword = $("#settings_password_repeat").val();
    let submitBtn = document.getElementById('submit_btn')
    if (newPassword.length === 0 && password.length > 0 ||
        newPassword.length > 0 && password.length === 0) {
        submitBtn.disabled = true;
    } else {
        submitBtn.disabled = newPassword !== confirmPassword;
    }
}

function checkPasswordCorrectness() {
    let password = $("#settings_password").val();
    let newPassword = $("#settings_new_password").val();
    let confirmPassword = $("#settings_password_repeat").val();
    let submitBtn = document.getElementById('submit_btn')
    if (password.length > 0) {
        submitBtn.disabled = password.length < 4
            || newPassword.length < 4
            || confirmPassword.length < 4;
    } else if (newPassword.length === 0 && confirmPassword.length === 0) {
        submitBtn.disabled = false;
    } else {
        submitBtn.disabled = false;
    }
}


$(document).ready(function () {
    $("#settings_password").keyup(checkPasswordCorrectness);
});

$(document).ready(function () {
    $("#settings_new_password").keyup(checkNewPasswordCorrectness);
});

$(document).ready(function () {
    $("#settings_password_repeat").keyup(checkConfirmPasswordMatch);
});

