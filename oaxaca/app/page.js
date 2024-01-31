import { Button } from '@/components/ui/button';

export default function Home() {
  return (
    <main>
      <h1>Ready? Oaxaca time!</h1>
      <p>
        Oaxaca is a vibrant Mexican restaurant offering an authentic dining
        experience. Join us for a culinary journey that's bursting with the
        unique flavors and textures of Mexico.
      </p>

      <Button href='/login'>Login</Button>
      <Button href='/signup'>Sign up</Button>
    </main>
  );
}
