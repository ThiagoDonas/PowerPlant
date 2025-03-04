import AppRoutes from "./routes";
import { GlobalStyles } from "./styles/globalStyles";
import { theme } from "./styles/theme";
import { ThemeProvider as StyledThemeProvider } from "styled-components";

const App = () => {
  return (
    <StyledThemeProvider theme={theme}>
      <GlobalStyles /> 
      <AppRoutes />
    </StyledThemeProvider>
  );
};

export default App;
