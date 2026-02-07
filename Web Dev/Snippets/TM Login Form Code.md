// components/ui/login-form.tsx
```
"use client"

  

import * as React from "react";

import { useActionState } from "react";

  

import { cn } from "@/lib/utils"

import { signIn } from "@/app/admin/login/actions";

  

import { Button } from "@/components/ui/button"

import {

  Card,

  CardContent,

  CardDescription,

  CardHeader,

  CardTitle,

} from "@/components/ui/card"

import {

  Field,

  FieldDescription,

  FieldGroup,

  FieldLabel,

  FieldSeparator,

} from "@/components/ui/field"

import { Input } from "@/components/ui/input"

  

export function LoginForm({

  className,

  ...props

}: React.ComponentProps<"div">) {

  const [state, formAction, pending] = useActionState(signIn, { error: "" });

  

  return (

    <div className={cn("flex flex-col gap-6", className)} {...props}>

      <Card>

        <CardHeader className="text-center">

          <CardTitle className="text-xl">Login to Admin Panel</CardTitle>

        </CardHeader>

        <CardContent>

          <form action={formAction}>

            <FieldGroup>

              {state?.error ? (

                <p className="text-sm text-destructive">{state.error}</p>

              ) : null}

              <Field>

                <FieldLabel htmlFor="email">Email</FieldLabel>

                <Input

                  id="email"

                  name="email"

                  type="email"

                  placeholder="m@example.com"

                  required

                />

              </Field>

              <Field>

                <div className="flex items-center">

                  <FieldLabel htmlFor="password">Password</FieldLabel>

                </div>

                <Input id="password" name="password" type="password" required />

              </Field>

              <Field>

                <Button type="submit" disabled={pending}>

                  {pending ? "Logging in..." : "Login"}

                </Button>

              </Field>

            </FieldGroup>

          </form>

        </CardContent>

      </Card>

    </div>

  )

}
```