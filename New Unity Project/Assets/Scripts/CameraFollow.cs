using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraFollow : MonoBehaviour
{

    public Transform ball;
    private Vector3 offset;

    void Start()
    {
        offset = this.transform.position - ball.position;
    }

    
    void Update()
    {
        this.transform.position = ball.position + offset;
    }
}
