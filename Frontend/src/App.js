import React, { useState } from 'react'
import axios from 'axios';



const App = () => {
  const [users, setUsers] = useState([]);

  const [id, setId] = useState('');
  const [city, setCity] = useState('');
  const [profession, setProfession] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');


  const handleSubmitID = async(e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/users/id/${id}`);
      setUsers([response.data]);
    } catch (err) {
      console.log(err);
    }
  }

  const handleSubmitCity = async(e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/users/city/${city}`);
      setUsers(response.data);
    } catch (err) {
      console.log(err);
    }
  }
  const handleSubmitName = async(e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/users/name`, {
        params: {
          firstName: firstName,
          lastName: lastName
        }
      });
      setUsers([response.data]);
    } catch (err) {
      console.log(err);
    }
  }
  const handleSubmitProfession = async(e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/users/profession`, {
        params: {
          profession
        }
      });
      setUsers(response.data);
    } catch (err) {
      console.log(err);
    }
  }
  const handleSubmitDateRange = async(e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/users/date-range`, {
        params: {
          startDate: startDate,
          endDate: endDate
        }
      });
      setUsers(response.data);
    } catch (err) {
      console.log(err);
    }
  }
  const handleSubmitAllUsers = async(e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/users/`);
      setUsers(response.data);
    } catch (err) {
      console.log(err);
    }
  }
  
  return (
    <div>
      <h2>UserApi Coding Test</h2>
      <form onSubmit={handleSubmitID}>
        <label>
          User ID:
          <input
            type="text"
            value={id}
            onChange={e => setId(e.target.value)}
            placeholder="Enter user id"
          />
        </label>
        <button type="submit">Get Users By Id</button>
      </form>
      
      <form onSubmit={handleSubmitName}>
          <br/>
          First Name:
          <input
            type="text"
            value={firstName}
            onChange={e => setFirstName(e.target.value)}
            placeholder="Enter First Name"
          />
          Last Name:
          <input
            type="text"
            value={lastName}
            onChange={e => setLastName(e.target.value)}
            placeholder="Enter Last Name"
          />

        <button type="submit">Get Users By Name</button>
        
      </form>
      <form onSubmit={handleSubmitCity}>
      <br/>
        <label>
          City:
          <input
            type="text"
            value={city}
            onChange={e => setCity(e.target.value)}
            placeholder="Enter user city"
          />
        </label>
        <button type="submit">Get Users By City</button>
      </form>
      <form onSubmit={handleSubmitProfession}>
      <br/>
        <label>
          Profession:
          <input
            type="text"
            value={profession}
            onChange={e => setProfession(e.target.value)}
            placeholder="Enter user profession"
          />
        </label>
        <button type="submit">Get Users By Profession</button>
      </form>
      <form onSubmit={handleSubmitDateRange}>
      <br/>
        <label>
          Date Range:
          <br/>
          Start:
          <input
            type="text"
            value={startDate}
            onChange={e => setStartDate(e.target.value)}
            placeholder="yyyy-mm-dd"
          />
          </label>

          End:
          <label>
            <input
            type="text"
            value={endDate}
            onChange={e => setEndDate(e.target.value)}
            placeholder="yyyy-mm-dd"
          />
        </label>
        <button type="submit">Get Users By Date Range</button>
      </form>
      <br/>

      <label>
        Get All Users:
      <button type='submit' onClick={handleSubmitAllUsers}>Get All Users</button>
      </label>

      <br/>
      <br/>

    <div>
    {users.length > 0 ? (
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Date Created</th>
          <th>First</th>
          <th>Last</th>
          <th>Email</th>
          <th>Profession</th>
          <th>City</th>
          <th>Country</th>
        </tr>
      </thead>
      <tbody>
        {users.map(user => (
          <tr key={user.id}>
            <td>{user.id}</td>
            <td>{user.dateCreated}</td>
            <td>{user.firstName}</td>
            <td>{user.lastName}</td>
            <td>{user.email}</td>
            <td>{user.profession}</td>
            <td>{user.city}</td>
            <td>{user.country}</td>
          </tr>
      ))}
      </tbody>
    </table>
    ) : (
      <p>No user found</p>
    )}
    </div>

    </div>

  )
}

export default App;
