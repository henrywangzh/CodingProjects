using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    public float speed;

    void FixedUpdate()
    {
        float horizontal;
        float vertical;

        horizontal = Input.GetAxis("Horizontal");
        vertical = Input.GetAxis("Vertical");

        Vector3 forceVector = new Vector3(horizontal, 0, vertical);
        this.GetComponent<Rigidbody>().AddForce(forceVector * speed);
    }
}
