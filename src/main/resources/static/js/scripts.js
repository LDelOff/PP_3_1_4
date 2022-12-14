function getUserFromForm(form) {
    var result = { };
    var count = 0;
    var roles = [];
    $.each($(form).serializeArray(), function() {
        if (this.name === 'roles') {
            roles[count] = this.value;
            count++;
        }
        else {
            result[this.name] = this.value;
        }
    });
    result.roles = roles;
    return result;
}

function getUserById(id) {
    var msg = $.ajax("/api/users/" + id, {
        async: false,
        method: "get",
        dataType: "json"
    }).responseJSON;

    return {
        id: msg.id,
        firstName: msg.firstName,
        lastName: msg.lastName,
        age: msg.age,
        email: msg.email,
        password: msg.password,
        roles: msg.roles
    };
}

function formModalDelete(obj) {
    var id = obj.value;
    var user = getUserById(id);
    $('#deleteUserModalFormID').val(user.id);
    $('#deleteUserModalFormFirstName').val(user.firstName);
    $('#deleteUserModalFormLastName').val(user.lastName);
    $('#deleteUserModalFormAge').val(user.age);
    $('#deleteUserModalFormEmail').val(user.email);
    $('#deleteUserModalFormPassword').val(user.password);
    $('#deleteUserModal').modal('toggle');
}

function formModalEdit(obj) {
    var id = obj.value;
    var user = getUserById(id);
    $('#editUserModalFormID').val(user.id);
    $('#editUserModalFormFirstName').val(user.firstName);
    $('#editUserModalFormLastName').val(user.lastName);
    $('#editUserModalFormAge').val(user.age);
    $('#editUserModalFormEmail').val(user.email);
    $('#editUserModalFormPassword').val(user.password);
    $('#editUserModal').modal('toggle');
}

function allRoles(el) {
    var result = "";
    for (var i = 0; i < el.roles.length; i++) {
        result = result + " ";
        result = result + el.roles[i].name.replace('ROLE_', "");
    }
    return result;
}

function addInformation() {
    $.ajax("/api/info", {
        method: "get",
        dataType: "json",
        success: function (msg) {
            var tbody = $("#users");
            var tr = $("<tr></tr>").addClass("user").appendTo(tbody);
            $("<td></td>").text(msg.id).appendTo(tr);
            $("<td></td>").text(msg.firstName).appendTo(tr);
            $("<td></td>").text(msg.lastName).appendTo(tr);
            $("<td></td>").text(msg.age).appendTo(tr);
            $("<td></td>").text(msg.email).appendTo(tr);
            $("<td></td>").text(allRoles(msg)).appendTo(tr);
        }
    });
}

function addInformationAdmin() {
    fetch("/api/users", {
        method: "GET"
    }).then((response) => response.json())
        .then((data) => {
            $("#users").html('');
            data.forEach(function (el) {
                var tbody = $('#users');
                var tr = $('<tr></tr>').appendTo(tbody);
                var td;
                $('<td></td>').text(el.id).appendTo(tr);
                $('<td></td>').text(el.firstName).appendTo(tr);
                $('<td></td>').text(el.lastName).appendTo(tr);
                $('<td></td>').text(el.age).appendTo(tr);
                $('<td></td>').text(el.email).appendTo(tr);
                $('<td></td>').text(allRoles(el)).appendTo(tr);
                td = $('<td></td>').appendTo(tr);
                $('<button></button>').text('Edit').attr('onClick', 'formModalEdit(this)')
                    .addClass('btn btn-primary')
                    .attr('data-target', '#editModal')
                    .attr('data-toggle', 'modal')
                    .val(el.id).appendTo(td);
                td = $('<td></td>').appendTo(tr);
                $('<button></button>').text('Delete').appendTo(td).attr('onClick', 'formModalDelete(this)')
                    .addClass('btn btn-danger')
                    .attr('data-target', '#deleteModal')
                    .attr('data-toggle', 'modal')
                    .val(el.id).appendTo(td);
            })
        });
}

addUserForm.onsubmit = async (e) => {
    e.preventDefault();
    var user = getUserFromForm(addUserForm);
    var re = /^[\w-\.]+@[\w-]+\.[a-z]{2,4}$/i;
    var myMail = user.email;
    var valid = re.test(myMail);
    if (valid) {
        let response = await fetch('/api/users/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(user),
        });
        addInformationAdmin();
        $('#nav-profile-tab').removeClass("active");
        $('#nav-profile').removeClass("active");
        $('#nav-profile').removeClass("show");
        $('#nav-home-tab').addClass("active");
        $('#nav-home').addClass("active");
        $('#nav-home').addClass("show");
    }

};

editUserModalForm.onsubmit = async (e) => {
    e.preventDefault();
    var user = getUserFromForm(editUserModalForm);
    var re = /^[\w-\.]+@[\w-]+\.[a-z]{2,4}$/i;
    var myMail = user.email;
    var valid = re.test(myMail);
    if (valid) {
        let response = await fetch('/api/users/', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(user),
        });
        $('#editUserModal').modal('hide')
        addInformationAdmin();
    }
};

deleteUserModalForm.onsubmit = async (e) => {
    e.preventDefault();
    var user = getUserFromForm(deleteUserModalForm);
    let response = await fetch('/api/users/' + user.id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user),
    });
    $('#deleteUserModal').modal('hide')
    addInformationAdmin()
};


