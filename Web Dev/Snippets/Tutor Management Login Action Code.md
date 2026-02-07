//app/admin/login/actions.ts
```
"use server";

  

import { redirect } from "next/navigation";

import { createClient } from "@/utils/supabase/server";

  

export async function signIn(_: unknown, formData: FormData) {

  const email = String(formData.get("email") ?? "");

  const password = String(formData.get("password") ?? "");

  

  const supabase = await createClient();

  

  const { error } = await supabase.auth.signInWithPassword({

    email,

    password,

  });

  

  if (error) {

    return { error: error.message };

  }

  

  redirect("/admin");

}
```
