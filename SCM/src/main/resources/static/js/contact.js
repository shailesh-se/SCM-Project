console.log("Contact.js");
const baseURL = "http://localhost:8081";
const viewContactModal = document.getElementById("view_contact_modal");

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    setTimeout(() => {
      contactModal.classList.add("scale-100");
    }, 50);
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "view_contact_mdoal",
  override: true,
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

async function loadContactdata(id) {
  //function call to load data
  console.log(id);
  try {
    const data = await (await fetch(`${baseURL}/api/contact/${id}`)).json();
    console.log(data);
    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.querySelector("#contact_image").src = data.picture;
    document.querySelector("#contact_address").innerHTML = data.address;
    document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
    document.querySelector("#contact_about").innerHTML = data.description ? data.description : "No description available";
    const contactFavorite = document.querySelector("#contact_favorite");
    if (data.favorite) {
      contactFavorite.innerHTML =
        "<i class='fas fa-star text-yellow-400'></i>";
    } else {
      contactFavorite.innerHTML = "Not a Favorite Contact";
    }

    document.querySelector("#contact_website").innerHTML = data.websiteLink ? data.websiteLink : "No Website Link Available";

    document.querySelector("#contact_linkedIn").innerHTML = data.linkedInLink ? data.linkedInLink : "No LinkedIn Profile Available";

    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}


// Delete Contact 
function deleteContact(id) {
    Swal.fire({
        title: "Do you want to delete the contact?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Delete",
    }).then((result) => {
        if (result.isConfirmed) {
            try {
                const url = `${baseURL}/user/contact/delete/${id}`;
                window.location.replace(url);
            } catch (error) {
                console.error("Error during redirect:", error);
                Swal.fire("Error", "Failed to delete contact", "error");
            }
        }
    });
}