"use client";

import { Button } from "@/components/ui/button";
import { toast } from "@/components/ui/use-toast";
import React from "react";

const handleClick = () => {
  toast({
    title: "Waiter is coming...",
    description: "Please wait a moment! Our waiter is coming to serve you!",
  });
};

export default function CallWaiterButton() {
  return (
    <Button className="text-white text-lg font-medium" onClick={handleClick}>
      Call Waiter
    </Button>
  );
}
