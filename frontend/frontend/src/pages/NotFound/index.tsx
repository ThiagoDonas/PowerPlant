import { Container, Typography } from "@mui/material";

const NotFound = () => {
  return (
    <Container>
      <Typography variant="h4">404 - Page Not Found</Typography>
      <Typography variant="body1">Oops! The page you are looking for does not exist.</Typography>
    </Container>
  );
};

export default NotFound;
