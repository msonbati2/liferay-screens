// WARNING
//
// This file has been generated automatically by Visual Studio from the outlets and
// actions declared in your storyboard file.
// Manual changes to this file will not be maintained.
//
using Foundation;
using System;
using System.CodeDom.Compiler;

namespace iOsSample
{
    [Register ("ViewController")]
    partial class ViewController
    {
        [Outlet]
        [GeneratedCode ("iOS Designer", "1.0")]
        BindingLibrary.LoginScreenlet loginscrlet { get; set; }

        [Outlet]
        [GeneratedCode ("iOS Designer", "1.0")]
        UIKit.UIButton myButton { get; set; }

        void ReleaseDesignerOutlets ()
        {
            if (loginscrlet != null) {
                loginscrlet.Dispose ();
                loginscrlet = null;
            }

            if (myButton != null) {
                myButton.Dispose ();
                myButton = null;
            }
        }
    }
}