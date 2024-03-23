'use client';

import { useToast } from '@/components/ui/use-toast';
import { createContext, useEffect, useState } from 'react';

export const AuthContext = createContext({
  user: null,
  loginUser: (user) => {},
});

export default function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const { toast } = useToast();

  useEffect(() => {
    async function validateRememberMeToken() {
      try {
        const response = await fetch('/api/auth/customer/login/validate', {
          method: 'GET',
          headers: { 'Content-Type': 'application/json' },
        });

        // First, check if the response is OK
        if (!response.ok) {
          const errorText = await response.text(); // Get the error message as text
          console.error('Error:', errorText);

          return;
        }

        // Then, safely parse the JSON
        const data = await response.text();
        console.log(data);
      } catch (error) {
        console.error('An error occurred:', error);
        toast({
          title: 'Sign up failed.',
          description: 'Please try again.',
        });
      }
    }

    validateRememberMeToken();
  }, []);

  const loginUser = (user) => setUser(user);
  const logoutUser = () => setUser(null);

  return (
    <AuthContext.Provider value={{ user, loginUser, logoutUser }}>
      {children}
    </AuthContext.Provider>
  );
}
