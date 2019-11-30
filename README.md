# SignzyGithubApp

This App lets you search for the particular user on the github with their username.
You can see their profile and public repository within the app itself.

# Library Used

1. CardView Library
2. Circular ImageView Library
3. Retrofit 2
4. RecyclerView
5. Picasso
6. Glide


# Github Api Integration

You can see the documentatio here https://developer.github.com/v3/ .

Two endpoints have been used to search the query but there is a limit on search. So be mindful of that.

**** First Endpoint to search for the User  *****

https://api.github.com/users/{username} with this endpoint you can search for the any user on the github and it will return OK message 
if data is returned and NOT FOUND message if user is not found.

**** Second Endpoint to search for the User repository  *****

https://api.github.com/users/{username}/repos with this endpoint you can search for  user repositories on the github.

It will return an array of Repository objects and from there you can get the data.


# Installation Guide

1. Android Version >= Lollipop.
2. Choose between downloading directly from apk or Android Studio.
3. If using apk give all permision from your phone to install the app.
4. If using android studio, download if not and then clone the repository.
5. After cloning open the file in android studio and enable developer option in you mobile phone.
6. Connect your phone with computer using USB.
5. Hit run form the toolbar and you will see your phone and select.
6. App will be installed if everything done accordingly.
