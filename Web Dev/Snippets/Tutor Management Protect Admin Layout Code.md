//app/admin/(protected)/layout.tsx
```
import { redirect } from "next/navigation";

import { createClient } from "@/utils/supabase/server";

  

export default async function ProtectedAdminLayout({

  children,

}: {

  children: React.ReactNode;

}) {

  const supabase = await createClient();

  

  // 1) Must be logged in

  const {

    data: { user },

    error: userError,

  } = await supabase.auth.getUser();

  

  if (userError || !user) {

    redirect("/admin/login");

  }

  

  // 2) Must be in admin allowlist (SQL function)

  const { data: isAdmin, error: adminError } = await supabase.rpc("is_admin");

  

  if (adminError || !isAdmin) {

    await supabase.auth.signOut();

    redirect("/admin/login?error=not_admin");

  }

  

  return <>{children}</>;

}
```