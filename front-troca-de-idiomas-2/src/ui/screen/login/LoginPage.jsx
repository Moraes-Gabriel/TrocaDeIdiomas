import React, { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { useGlobalError } from '../../context/error.context';
import { useGlobalUser } from '../../context/user.context';
import { useLoginApi } from '../../hooks/api/login/use-login-api.hook';
import './LoginPage.css';

function LoginPage() {
    const [inputValues, setInputValues] = useState();
    const [globalUser, setGlobalUser] = useGlobalUser();
    const [,setError] = useGlobalError();
    const navigate = useNavigate();

  const loginApi = useLoginApi();


    useEffect(() => {
      //localStorage.removeItem('token');
      //localStorage.removeItem('user');
    }, []);
  const handleChange = (eventoDeChange) => {
    const { name, value } = eventoDeChange.target;
    setInputValues({ ...inputValues, [name]: value });
  };


  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
        const user = await loginApi.logar(inputValues?.email, inputValues?.password);
        navigate("/inicio")

        console.log(user)
        setGlobalUser(user);
        localStorage.setItem('token', JSON.stringify(user))

      } catch (error) {
        console.log(error);
        setError(error.response?.data?.message)
      }
  };

  return (
    <div className="login-page">
      <div className="div-container">
        <h1 className="text-center">Login</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="formUsername">
            <Form.Label>Email</Form.Label>
            <Form.Control type="text" name='email' placeholder="Enter username" value={inputValues?.email} onChange={handleChange} />
          </Form.Group>

          <Form.Group controlId="formPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" name='password' placeholder="Password"  value={inputValues?.senha} onChange={handleChange} />
          </Form.Group>

          <Button variant="primary" type="submit" block>
            Login
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default LoginPage;